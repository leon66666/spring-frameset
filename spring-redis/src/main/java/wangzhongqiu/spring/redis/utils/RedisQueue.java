package wangzhongqiu.spring.redis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import wangzhongqiu.spring.redis.serializer.RedisBase64Serializer;
import wangzhongqiu.spring.redis.serializer.RedisSerializer;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.List;

public class RedisQueue<T> extends AbstractQueue<T> {

    private String key;

    private RedisManager manager;

    private RedisSerializer serializer;

    public RedisQueue(String key, JedisPool pool) {
        this.serializer = new RedisBase64Serializer();
        this.manager = new RedisManager(pool);
        this.key = key;
    }

    public RedisQueue(String key, JedisPool pool, RedisSerializer serializer) {
        this.serializer = serializer;
        this.manager = new RedisManager(pool);
        this.key = key;
    }

    @Override
    public boolean offer(final T element) {
        final String stringElement = serializer.serialize(element);
        return manager.request(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRequest(Jedis jedis) {
                return jedis.rpush(key, stringElement) > 0;
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public T poll() {
        String stringElement = manager.request(new RedisCallback<String>() {
            @Override
            public String doInRequest(Jedis jedis) {
                return jedis.lpop(key);
            }
        });
        return (T) serializer.deserialize(stringElement);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        String stringElement = manager.request(new RedisCallback<String>() {
            @Override
            public String doInRequest(Jedis jedis) {
                return jedis.lindex(key, 0);
            }
        });
        return (T) serializer.deserialize(stringElement);
    }

    @Override
    public boolean remove(Object element) {
        final String stringElement = serializer.serialize(element);
        Long removed = manager.request(new RedisCallback<Long>() {
            @Override
            public Long doInRequest(Jedis jedis) {
                return jedis.lrem(key, 1, stringElement);
            }
        });
        return removed >= 1;
    }

    @Override
    public void clear() {
        manager.request(new RedisCallback<Object>() {
            @Override
            public Object doInRequest(Jedis jedis) {
                jedis.del(key);
                return null;
            }
        });
    }

    @Override
    public int size() {
        return manager.request(new RedisCallback<Integer>() {
            @Override
            public Integer doInRequest(Jedis jedis) {
                return jedis.llen(key).intValue();
            }
        });
    }

    /**
     * The method is not recommended, since it requires a snapshot of the queue
     * which takes O(n).
     */
    @Override
    public boolean contains(final Object element) {
        String stringElement = serializer.serialize(element);
        Iterator<String> itr = snapshot().iterator();
        while (itr.hasNext()) {
            if (itr.next().equals(stringElement)) {
                return true;
            }
        }
        return false;
    }

    private List<String> snapshot() {
        return manager.request(new RedisCallback<List<String>>() {
            @Override
            public List<String> doInRequest(Jedis jedis) {
                return jedis.lrange(key, 0, -1);
            }
        });
    }

    /**
     * The method is not recommended; similar to method contains, it requires a
     * snapshot. And, the iterator cannot get updated, all methods of the
     * iterator are based on the snapshot not the queue itself in Redis.
     */
    @Override
    public Iterator<T> iterator() {
        return new Itr(this, snapshot());
    }

    private class Itr implements Iterator<T> {

        Iterator<String> iterator;

        RedisQueue<T> queue;

        T theNext;

        public Itr(RedisQueue<T> queue, List<String> snapshot) {
            this.queue = queue;
            this.iterator = snapshot.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            String stringElement = iterator.next();
            theNext = (T) serializer.deserialize(stringElement);
            return theNext;
        }

        @Override
        public void remove() {
            queue.remove(theNext);
            iterator.remove();
        }

    }

    public String getKey() {
        return key;
    }

    public boolean offerFirst(final T element) {
        final String stringElement = serializer.serialize(element);
        return manager.request(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRequest(Jedis jedis) {
                return jedis.lpush(key, stringElement) > 0;
            }
        });
    }

}
