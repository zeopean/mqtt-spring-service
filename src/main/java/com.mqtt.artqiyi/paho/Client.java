package com.mqtt.artqiyi.paho;

/**
 * Created with idea
 * Description: 客户端接收消息
 *
 * @author zeopean
 * Date: 2018-11-14
 */

import com.mqtt.artqiyi.common.CacheKey;
import com.mqtt.artqiyi.config.MqttProperties;
import com.mqtt.artqiyi.utils.cache.CacheExpiredUtil;
import com.mqtt.artqiyi.utils.cache.RedisUtil;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Client {

    @Autowired
    private static RedisUtil cache;

    @Autowired
    public void setCache(RedisUtil cache) {
        Client.cache = cache;
    }

    private static MqttClient client;
    private static MqttConnectOptions options;

    private MqttConnectOptions getOptions() {
        // MQTT的连接设置
        options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(true);
        // 设置连接的用户名,密码
        options.setUserName(MqttProperties.MQTT_USER_NAME);
        options.setPassword(MqttProperties.MQTT_PASSWORD.toCharArray());
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(3);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*3秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(3);
        options.setAutomaticReconnect(true);

        return options;
    }

    public void start(boolean reconnect) {
        try {
            if (null == client) {
                // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
                client = new MqttClient(MqttProperties.MQTT_HOST, MqttProperties.MQTT_CLIENTID, new MemoryPersistence());
                client.setCallback(new PushCallback());
            }
            if (client.isConnected()) {
                if (reconnect) {
                    client.disconnect();
                    client.connect(getOptions());
                }
            } else {
                client.connect(getOptions());
            }

            //订阅消息
            String[] topic = cache.get(CacheKey.CK_TOPICS, String[].class);
            client.subscribe(topic);
//            subsrube(client, topic);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void subsrube(MqttClient client, String[] topics) {
        if (null == topics) {
            return;
        }
        String cacheKey;
        try {
            for (String topic : topics) {
                // 判断是否已经发布信息，必须保证使用的是同一个 redis 服务器
                cacheKey = String.format(CacheKey.CK_TOPIC_IS_SUBSCRIBE, topic);
                if (!cache.exists(cacheKey)) {
                    cache.set(cacheKey, 1, CacheExpiredUtil.getSecond(3));
                    client.subscribe(topic);
                }
                cache.remove(cacheKey);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

}
