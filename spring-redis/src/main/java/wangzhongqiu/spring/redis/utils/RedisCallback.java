package wangzhongqiu.spring.redis.utils;

import redis.clients.jedis.Jedis;

public interface RedisCallback<T> {

    T doInRequest(Jedis jedis);

}
