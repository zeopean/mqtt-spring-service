package com.mqtt.artqiyi.paho;

/**
 * Created with idea
 * Description: 这是发送消息的服务端
 *
 * @author zeopean
 * Date: 2018-11-14
 */

import com.mqtt.artqiyi.config.MqttProperties;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Server {

    private static final String serviceId = "server11";
    private MqttClient client;
    private MqttTopic topic11;


    private MqttMessage message;

    /**
     * 构造函数
     * @throws MqttException
     */
    public Server() throws MqttException {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(MqttProperties.MQTT_HOST, serviceId, new MemoryPersistence());
        connect();
    }

    /**
     *  用来连接服务器
     */
    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(MqttProperties.MQTT_USER_NAME);
        options.setPassword(MqttProperties.MQTT_PASSWORD.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
//            client.setCallback(new PushCallback());
            client.connect(options);

            topic11 = client.getTopic(MqttProperties.MQTT_DEFAULT_TOPIC);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param topic
     * @param message
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    public void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
            MqttException {
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
        System.out.println("message is published completely! " + token.isComplete());
    }

    /**
     *  启动入口
     * @param args
     * @throws MqttException
     */
//    public static void main(String[] args) throws MqttException {
//        ServerMQTT server = new ServerMQTT();
//
//        server.message = new MqttMessage();
//        //保证消息能到达
//        server.message.setQos(1);
//        server.message.setRetained(true);
//        server.message.setPayload(("publish message! " + System.currentTimeMillis()).getBytes());
//        server.publish(server.topic11 , server.message);
//        System.out.println("----------isRetained--------");
//    }
}
