package com.mqtt.artqiyi.listeners;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created with idea
 * Description:
 *
 * @author zeopean
 * Date: 2018-11-22
 */
public class TopicListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message instanceof MqttMessage) {
            System.out.print("mqtt message" + ((MqttMessage) message).getPayload());
        }
    }
}
