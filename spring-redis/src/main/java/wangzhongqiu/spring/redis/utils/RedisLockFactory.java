package wangzhongqiu.spring.redis.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class RedisLockFactory {

    public Lock newLock(Jedis jedis, String key) {
        return new RedisLock(jedis, key);
    }

    private class RedisLock implements Lock {

        private static final long TRYLOCK_INTERVAL = 5; // milliseconds

        private static final int TRYLOCK_RANDOM_RANGE = 1000000; // nanoseconds

        private static final int EXPIRE_TIME = 30000; // milliseconds

        private Jedis jedis;

        private String key;

        private String timestamp;

        protected long currentTimeMillis() {
            jedis.connect();
            List<String> time = jedis.time();
            Long secPart = Long.parseLong(time.get(0)) * 1000;
            Long microPart = Long.parseLong(time.get(1)) / 1000;
            return secPart + microPart;
        }

        public RedisLock(Jedis jedis, String key) {
            this.jedis = jedis;
            this.key = key;
        }

        @Override
        public void lock() {
            try {
                lockInterruptibly();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            while (!tryLock()) {
                Thread.sleep(TRYLOCK_INTERVAL, (int) (Math.random() * TRYLOCK_RANDOM_RANGE));
            }
        }

        @Override
        public Condition newCondition() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean tryLock() {
            try {
                jedis.connect();
                long currentTs = currentTimeMillis();
                String expires = String.valueOf(currentTs + EXPIRE_TIME);
                if (jedis.setnx(key, expires) == 1) {
                    timestamp = expires;
                    return true;
                }
                String lockingTs = jedis.get(key);
                if (lockingTs != null && Long.parseLong(lockingTs) < currentTs) {
                    String lockingTs2 = jedis.getSet(key, expires);
                    if (lockingTs2 != null && lockingTs2.equals(lockingTs)) {
                        timestamp = expires;
                        return true;
                    }
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
            timeout = unit.toMillis(timeout);
            while (!tryLock()) {
                if (timeout <= 0) {
                    return false;
                }
                long beforeSleepTs = System.currentTimeMillis();
                Thread.sleep(TRYLOCK_INTERVAL, (int) (Math.random() * TRYLOCK_RANDOM_RANGE));
                long afterSleepTs = System.currentTimeMillis();
                timeout = timeout - (afterSleepTs - beforeSleepTs);
            }
            return false;
        }

        @Override
        public void unlock() {
            if (timestamp != null) {
                jedis.connect();
                jedis.watch(key);

                String lockingTs = jedis.get(key);
                if (lockingTs != null && lockingTs.equals(timestamp) && Long.parseLong(lockingTs) > currentTimeMillis()) {
                    Transaction tx = jedis.multi();
                    tx.del(key);
                    tx.exec();
                } else {
                    jedis.unwatch();
                }

                timestamp = null;
            }
        }
    }

}
