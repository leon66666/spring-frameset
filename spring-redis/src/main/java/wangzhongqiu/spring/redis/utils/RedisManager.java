package wangzhongqiu.spring.redis.utils;

import org.apache.commons.io.IOUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class RedisManager {

    private JedisPool pool;

    private RedisLockFactory locksmith = new RedisLockFactory();

    private HashMap<String, String> scripts = new HashMap<String, String>();

    public RedisManager(JedisPool pool) {
        this.pool = pool;
    }

    public String loadScript(String name) throws IOException {
        if (scripts.get(name) == null) {
            InputStream is = RedisManager.class.getResourceAsStream("/redis-scripts/" + name + ".lua");
            scripts.put(name, IOUtils.toString(is));
        }
        return scripts.get(name);
    }

    public <T> T request(RedisCallback<T> callback) {
        Jedis jedis = pool.getResource();
        try {
            jedis.connect();
            return callback.doInRequest(jedis);
        } catch (JedisConnectionException jce) {
            pool.returnBrokenResource(jedis);
            jedis = null;
            throw jce;
        } finally {
            if (jedis != null) {
                pool.returnResource(jedis);
                jedis = null;
            }
        }
    }

    public <T> T request(RedisCallback<T> callback, String lockKey) {
        Jedis jedis = pool.getResource();
        Lock lock = locksmith.newLock(jedis, lockKey);
        lock.lock();
        try {
            jedis.connect();
            return callback.doInRequest(jedis);
        } catch (JedisConnectionException jce) {
            pool.returnBrokenResource(jedis);
            jedis = null;
            throw jce;
        } finally {
            lock.unlock();
            if (jedis != null) {
                pool.returnResource(jedis);
                jedis = null;
            }
        }
    }

    public long currentTimeMillis() {
        List<String> time = request(new RedisCallback<List<String>>() {
            @Override
            public List<String> doInRequest(Jedis jedis) {
                return jedis.time();
            }
        });
        Long secPart = Long.parseLong(time.get(0)) * 1000;
        Long microPart = Long.parseLong(time.get(1)) / 1000;
        return secPart + microPart;
    }

}
