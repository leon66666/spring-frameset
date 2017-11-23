package wangzhongqiu.spring.redis;

public class RedisService {

    RedisUtil redisBusiness;

    RedisUtil redisCache;

    RedisUtil redisSession;


    public RedisService(RedisUtil redisBusiness, RedisUtil redisCache, RedisUtil redisSession) {
        this.redisBusiness = redisBusiness;
        this.redisCache = redisCache;
        this.redisSession = redisSession;
    }

    public RedisUtil getRedisBusiness() {
        return redisBusiness;
    }


    public RedisUtil getRedisCache() {
        return redisCache;
    }


    public RedisUtil getRedisSession() {
        return redisSession;
    }
}
