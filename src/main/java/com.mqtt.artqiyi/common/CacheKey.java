package com.mqtt.artqiyi.common;

/**
 * Created with idea
 * Description:
 *
 * @author zeopean
 * Date: 2018-11-22
 */
public interface CacheKey {

    String CK_TOPICS = "ck.mqtt.service";
    String CK_TOPICS_FRESH = "ck.mqtt.topics.is.refresh";
    String CK_TOPIC_IS_SUBSCRIBE = "ck.mqtt.topic.is.subscribe.%s";
}
