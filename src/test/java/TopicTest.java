import com.mqtt.artqiyi.common.CacheKey;
import com.mqtt.artqiyi.utils.cache.RedisUtil;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created with idea
 * Description:
 *
 * @author zeopean
 * Date: 2018-11-22
 */
public class TopicTest extends BaseTests {

    private boolean lock = false;

    @Resource
    private RedisUtil cache;

    @Test
    public void redisTopic()
    {
        String[] topics = new String[]{"hello", "default_topic", "active123"};
        boolean res = cache.set(CacheKey.CK_TOPICS, topics);
        cache.set(CacheKey.CK_TOPICS_FRESH, 1);
    }

    @Test
    public void connectCount()
    {
        // activateMQ 连接数
        int count = 0;

    }
}
