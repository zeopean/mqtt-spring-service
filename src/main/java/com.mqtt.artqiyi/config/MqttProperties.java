package com.mqtt.artqiyi.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with idea
 * Description:
 *
 * @author zeopean
 * Date: 2018-11-13
 */
public class MqttProperties {

    public static String MQTT_HOST;
    public static String MQTT_CLIENTID;
    public static String MQTT_USER_NAME;
    public static String MQTT_PASSWORD;
    public static String MQTT_DEFAULT_TOPIC;
    public static int MQTT_TIMEOUT;
    public static int MQTT_KEEP_ALIVE;


    static {
        MQTT_HOST = loadMqttProperties().getProperty("artqiyi.mqtt.host");
        MQTT_CLIENTID = loadMqttProperties().getProperty("artqiyi.mqtt.clientid");
        MQTT_USER_NAME = loadMqttProperties().getProperty("artqiyi.mqtt.username");
        MQTT_PASSWORD = loadMqttProperties().getProperty("artqiyi.mqtt.password");
        MQTT_DEFAULT_TOPIC = loadMqttProperties().getProperty("artqiyi.mqtt.topic");
        MQTT_TIMEOUT = Integer.valueOf(loadMqttProperties().getProperty("artqiyi.mqtt.timeout"));
        MQTT_KEEP_ALIVE = Integer.valueOf(loadMqttProperties().getProperty("artqiyi.mqtt.keepalive"));

    }

    private static Properties loadMqttProperties() {
        InputStream inputstream = MqttProperties.class.getResourceAsStream("/application-busi.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputstream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputstream != null) {
                    inputstream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
