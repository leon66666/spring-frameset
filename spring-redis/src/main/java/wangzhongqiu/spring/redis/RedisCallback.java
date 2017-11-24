package wangzhongqiu.spring.redis;

import redis.clients.jedis.Jedis;

public interface RedisCallback<T> {

    T doInRequest(Jedis jedis);

}
