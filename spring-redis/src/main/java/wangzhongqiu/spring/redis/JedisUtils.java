package wangzhongqiu.spring.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;

/**
 * Jedis工具类
 */
public class JedisUtils {

    private static Logger logger = LoggerFactory.getLogger(JedisUtils.class);

    /**
     * 处理jedis异常，判断连接是否被破坏了
     * @param jedisException jedis异常
     * @param jedisPool jedis连接池
     * @return
     */
    public static boolean handleJedisException(Exception jedisException, JedisPool jedisPool) {
        if (jedisException instanceof JedisConnectionException) {
            logger.error("Redis connection lost.", jedisException);
        } else if (jedisException instanceof JedisDataException) {
            if ((jedisException.getMessage() != null) && (jedisException.getMessage().indexOf("READONLY") != -1)) {
                logger.error("Redis connection are read-only slave.", jedisException);
            } else {
                return false;
            }
        } else {
            logger.error("Jedis exception happen.", jedisException);
        }
        return true;
    }

    /**
     * 根据状态关闭连接
     * @param jedis jedis连接
     * @param connectionBroken 连接是否被破坏
     * @param jedisPool jedis连接池
     */
    public static void closeResource(Jedis jedis, boolean connectionBroken, JedisPool jedisPool) {
        try {
            if (connectionBroken) {
                jedisPool.returnBrokenResource(jedis);
            } else {
                jedisPool.returnResource(jedis);
            }
        } catch (Exception e) {
            logger.error("return back jedis failed, will fore close the jedis.", e);
        }
    }
}
