package com.mqtt.artqiyi.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created with idea
 * Description: Mqtt 服务配置组装类
 *
 * @author zeopean
 * Date: 2018-11-13
 */
@Component
@Data
@PropertySource("classpath:application-busi.properties")
public class MqttConfiguration {

    @Value("${artqiyi.mqtt.host}")
    private String host;

    @Value("${artqiyi.mqtt.clientid}")
    private String clientid;

    @Value("${artqiyi.mqtt.topic}")
    private String topic;

    @Value("${artqiyi.mqtt.username}")
    private String username;

    @Value("${artqiyi.mqtt.password}")
    private String password;

    @Value("${artqiyi.mqtt.timeout}")
    private String timeout;

    @Value("${artqiyi.mqtt.keepalive}")
    private String keepalive;
//
//
//    @Bean
//    public MqttConnectOptions getMqttConnectOptions() {
//        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
//        mqttConnectOptions.setUserName(username);
//        mqttConnectOptions.setPassword(password.toCharArray());
//        mqttConnectOptions.setServerURIs(new String[]{host});
//        mqttConnectOptions.setKeepAliveInterval(2);
//        return mqttConnectOptions;
//    }
//
//    @Bean
//    public MqttPahoClientFactory mqttClientFactory() {
//        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
//        factory.setConnectionOptions(getMqttConnectOptions());
//        return factory;
//    }
//
//    @Bean
//    @ServiceActivator(inputChannel = "mqttOutboundChannel")
//    public MessageHandler mqttOutbound() {
//        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(clientid, mqttClientFactory());
//        messageHandler.setAsync(true);
//        messageHandler.setDefaultTopic(topic);
//        return messageHandler;
//    }
//
//    @Bean
//    public MessageChannel mqttOutboundChannel() {
//        return new DirectChannel();
//    }

}
