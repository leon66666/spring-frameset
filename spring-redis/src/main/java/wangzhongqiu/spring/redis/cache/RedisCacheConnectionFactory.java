package wangzhongqiu.spring.redis.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import zhongqiu.javautils.StringUtil;

import java.io.Serializable;

/**
 * spring-cache放在redis中
 */
@Service
public class RedisCacheConnectionFactory implements Cache {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    /**
     * 缓存名称
     */
    private String name;

    /**
     * 超时时间
     */
    private long timeout;

    /*
     * (non-Javadoc)
     * @see org.springframework.cache.Cache#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.cache.Cache#getNativeCache()
     */
    @Override
    public Object getNativeCache() {
        // TODO Auto-generated method stub
        return this.redisTemplate;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.cache.Cache#get(java.lang.Object)
     */
    @Override
    public ValueWrapper get(Object key) {
        if (StringUtil.isEmpty(key)) {
            return null;
        } else {
            final String finalKey;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            Object object = null;
            object = redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    byte[] key = finalKey.getBytes();
                    byte[] value = connection.get(key);
                    if (StringUtil.isEmpty(value) || value.length == 0) {
                        return null;
                    }
                    return StringUtil.deserialize(value);
                }
            });
            return (object != null ? new SimpleValueWrapper(object) : null);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.cache.Cache#get(java.lang.Object, java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Class<T> type) {
        if (StringUtil.isEmpty(key) || null == type) {
            return null;
        } else {
            final String finalKey;
            final Class<T> finalType = type;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            final Object object = redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    byte[] key = finalKey.getBytes();
                    byte[] value = connection.get(key);
                    if (StringUtil.isEmpty(value) || value.length == 0) {
                        return null;
                    }
                    return StringUtil.serialize(value);
                }
            });
            if (finalType != null && finalType.isInstance(object) && null != object) {
                return (T) object;
            } else {
                return null;
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.cache.Cache#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public void put(final Object key, final Object value) {
        if (StringUtil.isEmpty(key)) {
            return;
        } else {
            final String finalKey;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            if (!StringUtil.isEmpty(finalKey)) {
                final Serializable finalValue = (Serializable) value;
                redisTemplate.execute(new RedisCallback<Boolean>() {
                    @Override
                    public Boolean doInRedis(RedisConnection connection) {
                        if (value != null) {
                            connection.set(finalKey.getBytes(), StringUtil.serialize(finalValue));
                        } else {
                            connection.set(finalKey.getBytes(), "".getBytes());
                        }
                        // 设置超时间
                        if (timeout > 0) {
                            connection.expire(finalKey.getBytes(), timeout);
                        }
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        return null;
    }

    /*
     * 根据Key 删除缓存
     */
    @Override
    public void evict(Object key) {
        if (null != key) {
            final String finalKey;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            if (!StringUtil.isEmpty(finalKey)) {
                redisTemplate.execute(new RedisCallback<Long>() {
                    public Long doInRedis(RedisConnection connection) throws DataAccessException {
                        return connection.del(finalKey.getBytes());
                    }
                });
            }
        }
    }

    /*
     * 清楚系统缓存
     */
    @Override
    public void clear() {
        // TODO Auto-generated method stub
        // redisTemplate.execute(new RedisCallback<String>() {
        // public String doInRedis(RedisConnection connection) throws DataAccessException {
        // connection.flushDb();
        // return "ok";
        // }
        // });
    }

    public RedisTemplate<String, Serializable> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}