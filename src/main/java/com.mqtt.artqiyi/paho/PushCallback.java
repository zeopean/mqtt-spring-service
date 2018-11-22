package com.mqtt.artqiyi.paho;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created with idea
 * Description:
 *
 * @author zeopean
 * Date: 2018-11-14
 */
public class PushCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("连接断开，可以做重连");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("---------deliveryComplete---------");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {

        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + message.getQos());
        System.out.println("接收消息内容 : " + new String(message.getPayload()));
    }

}
