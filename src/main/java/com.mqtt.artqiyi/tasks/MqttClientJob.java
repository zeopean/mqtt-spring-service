package com.mqtt.artqiyi.tasks;

import com.mqtt.artqiyi.common.CacheKey;
import com.mqtt.artqiyi.paho.Client;
import com.mqtt.artqiyi.utils.cache.RedisUtil;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created with idea
 * Description:
 *
 * @author zeopean
 * Date: 2018-11-22
 */
@Component
public class MqttClientJob {

    private static boolean lock = false;

    @Autowired
    private RedisUtil cache;

    @Scheduled(cron = "* * * * * ?")
    public void clientRefresh() throws MqttException {
        Integer isRefresh = cache.get(CacheKey.CK_TOPICS_FRESH, Integer.class);
        if (null != isRefresh && isRefresh > 0) {
            // 状态重置
            cache.set(CacheKey.CK_TOPICS_FRESH, 0);

            new Client().start(true);
            lock = true;
        }
        if (!lock) {
            new Client().start(false);
        }
        lock = true;
    }
}
