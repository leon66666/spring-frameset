package wangzhongqiu.spring.redis.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import wangzhongqiu.spring.core.exception.RedisConnectException;
import wangzhongqiu.spring.core.exception.base.RedisException;
import wangzhongqiu.spring.redis.constant.Constants;
import wangzhongqiu.spring.redis.constant.SupervisionConfig;
import wangzhongqiu.spring.redis.service.RedisCommonService;
import wangzhongqiu.spring.redis.utils.JedisUtils;
import wangzhongqiu.spring.redis.utils.RedisCallback;
import wangzhongqiu.spring.redis.utils.RedisManager;
import zhongqiu.javautils.StringUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Redis业务类。servicePool
 */
@Service
public class RedisCommonServiceImpl implements RedisCommonService {

    private static Logger logger = LoggerFactory.getLogger(RedisCommonServiceImpl.class);

    private final static String strCharset = "UTF-8";

    @Resource(name = "servicePool")
    private JedisPool servicePool;

    private RedisManager redisManager;

    @PostConstruct
    void init() {
        redisManager = new RedisManager(servicePool);
    }

    /**
     * Redis连接
     *
     * @return
     */
    private Jedis connect(JedisPool pool) throws RedisConnectException {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.connect();
        } catch (Exception e) {
            logger.error("Redis连接失败：", e);
            throw new RedisConnectException("Redis连接失败：", e);
        }
        return jedis;
    }

    /**
     * 自选择jedis pool
     *
     * @param key
     * @return
     */
    private JedisPool choosePool(String key) {
//		JedisPool pool = servicePool;
//		try {
//			switch (Constants.sharding(key)) {
//			case COMMON:
//				pool = servicePool;
//				break;
//			case LOANTRANSFER:
//				pool = loanTransferPool;
//				break;
//			case UPLAN_LOAN:
//				pool = loanPool;
//				break;
//			default:
//				pool = servicePool;
//				break;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        return servicePool;
    }

    /**
     * 追加
     *
     * @param key
     * @param value
     * @return 追加后字符串的长度
     */
    @Override
    public long append(String key, String value) {
        if (StringUtils.isEmpty(key)) {
            return 0;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return 0;
            }
            return jedis.append(key, value);
        } catch (Exception e) {
            logger.error("追加失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return 0;
    }

    /**
     * 删除
     *
     * @param key
     * @return
     */
    @Override
    public long del(String key) {
        if (StringUtils.isEmpty(key)) {
            return 0;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return 0;
            }
            return jedis.del(key);
        } catch (Exception e) {
            logger.error("删除失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return 0;
    }

    /**
     * 获取
     *
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            return jedis.get(key);
        } catch (Exception e) {
            logger.error("获取失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return null;
    }

    /**
     * 设置
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public String set(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return null;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            return jedis.set(key, value);
        } catch (Exception e) {
            logger.error("设置失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return null;
    }

    /**
     * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)
     *
     * @param key
     * @param value
     * @param expireSeconds
     * @return
     */
    @Override
    public String setex(String key, String value, int expireSeconds) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return null;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            String test = jedis.setex(key, expireSeconds, value);
            return test;
        } catch (Exception e) {
            logger.error("设置失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return null;
    }

    @Override
    public void setStr(String keyPrefix, String key, String value, int expireSeconds) {
        JedisPool pool = choosePool(keyPrefix + key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = pool.getResource();
            jedis.connect();
            jedis.setex(keyPrefix + key, expireSeconds, value);
        } catch (Exception e) {
            e.printStackTrace();
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    /**
     * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)
     * 如果get有值不会重新setex
     *
     * @param key
     * @param value
     * @param expireSeconds
     * @return
     */
    @Override
    public String setexNoContinue(String key, String value, int expireSeconds) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return null;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            if (StringUtils.isNotBlank(jedis.get(key))) {
                return null;
            }
            return jedis.setex(key, expireSeconds, value);
        } catch (Exception e) {
            logger.error("设置失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return null;
    }

    @Override
    public void setObj(String keyPrefix, String key, Serializable value, int expireSeconds) {
        JedisPool pool = choosePool(keyPrefix + key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = pool.getResource();
            jedis.connect();
            byte[] vb = StringUtil.serialize(value);
            jedis.setex((keyPrefix + key).getBytes(strCharset), expireSeconds, vb);
        } catch (Exception e) {
            e.printStackTrace();
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    @Override
    public void setObj(String keyPrefix, String key, Serializable value) {
        JedisPool pool = choosePool(keyPrefix + key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = pool.getResource();
            jedis.connect();
            byte[] vb = StringUtil.serialize(value);
            jedis.set((keyPrefix + key).getBytes(strCharset), vb);
        } catch (Exception e) {
            e.printStackTrace();
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    @Override
    public Serializable getObj(String keyPrefix, String key) {
        JedisPool pool = choosePool(keyPrefix + key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = pool.getResource();
            jedis.connect();
            byte[] bytes = jedis.get((keyPrefix + key).getBytes(strCharset));
            if (null == bytes) {
                return null;
            } else {
                Serializable obj = StringUtil.deserialize(bytes);
                return obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            return null;
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    /**
     * setStr过期时间永久
     */
    @Override
    public void setStrAndPersist(String key, String value) {
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = pool.getResource();
            jedis.connect();
            jedis.set(key, value);
            jedis.persist(key);
        } catch (Exception e) {
            e.printStackTrace();
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    @Override
    public String getStr(String keyPrefix, String key) {
        JedisPool pool = choosePool(keyPrefix + key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = pool.getResource();
            jedis.connect();
            return jedis.get(keyPrefix + key);
        } catch (Exception e) {
            e.printStackTrace();
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            return null;
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    @Override
    public void setList(String keyPrefix, String key, List<String> keys, int expireSeconds) {
        JedisPool pool = choosePool(keyPrefix + key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = pool.getResource();
            jedis.connect();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            for (String element : keys) {
                out.writeUTF(element);
            }
            byte[] bytes = baos.toByteArray();
            jedis.setex((keyPrefix + key).getBytes(strCharset), expireSeconds, bytes);
        } catch (Exception e) {
            e.printStackTrace();
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    @Override
    public List<String> getList(String keyPrefix, String key) {
        JedisPool pool = choosePool(keyPrefix + key);
        Jedis jedis = null;
        boolean connectionBroken = false;
        List<String> list = new ArrayList<String>();
        try {
            jedis = pool.getResource();
            jedis.connect();
            byte[] bytes = jedis.get((keyPrefix + key).getBytes(strCharset));
            if (null == bytes) {
                return null;
            }
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            DataInputStream in = new DataInputStream(bais);
            while (in.available() > 0) {
                String element = in.readUTF();
                list.add(element);
            }
        } catch (Exception e) {
            e.printStackTrace();
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return list;
    }

    @Override
    public void lset(String keyPrefix, String key, List<Serializable> values) throws RedisConnectException {
        JedisPool pool = choosePool(keyPrefix + key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = pool.getResource();
            jedis.connect();
            byte[] k = (keyPrefix + key).getBytes(strCharset);
            for (int i = 0, j = values.size(); i < j; i++) {
                jedis.lset(k, i, StringUtil.serialize(values.get(i)));
            }
        } catch (Exception e) {
            logger.error("lset失败[" + keyPrefix + "]：" + e.getMessage(), e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("lset失败[" + keyPrefix + "]：" + e.getMessage(), e);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    @Override
    public Long lrem(String keyPrefix, String key, long count, String value) throws RedisConnectException {
        JedisPool pool = choosePool(keyPrefix + key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = pool.getResource();
            jedis.connect();
            return jedis.lrem(keyPrefix + key, count, value);
        } catch (Exception e) {
            logger.error("redis设置值失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("设置redis value失败，未知异常");
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    @Override
    public List<String> lrange(String keyPrefix, String key, int start, int end) {
        JedisPool pool = choosePool(keyPrefix + key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = pool.getResource();
            jedis.connect();
            byte[] k = (keyPrefix + key).getBytes(strCharset);
            List<byte[]> bb = jedis.lrange(k, start, end);

            List<String> objs = new CopyOnWriteArrayList<String>();
            for (int i = 0, j = bb.size(); i < j; i++) {
                objs.add(new String(bb.get(i)));
            }
            return objs;
        } catch (Exception e) {
            e.printStackTrace();
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            return null;
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    @Override
    public List<String> lrange(String key, int start, int end) {
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = pool.getResource();
            jedis.connect();
            List<String> bb = jedis.lrange(key, start, end);
            List<String> objs = new CopyOnWriteArrayList<String>();
            for (int i = 0, j = bb.size(); i < j; i++) {
                objs.add(bb.get(i));
            }
            return objs;
        } catch (Exception e) {
            e.printStackTrace();
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            return null;
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    @Override
    public Map<String, String> hgetAll(String keyPrefix, String key) {
        JedisPool pool = choosePool(keyPrefix + key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = pool.getResource();
            jedis.connect();
            Map<String, String> result = jedis.hgetAll(keyPrefix + key);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            return null;
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    /**
     * 根据key和field获取value
     *
     * @param key
     * @param field
     * @return
     */
    @Override
    public String hget(String key, String field) {
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        String result = null;
        try {
            jedis = pool.getResource();
            jedis.connect();
            result = jedis.hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return result;
    }

    @Override
    public void mset(String keyPrefix, List<String> keys, List<Serializable> values) throws RedisConnectException {
        JedisPool pool = choosePool(keyPrefix);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = pool.getResource();
            jedis.connect();
            List<String> s = new ArrayList<String>();
            for (int i = 0, j = keys.size(); i < j; i++) {
                s.add(keyPrefix + keys.get(i));
                s.add(new String(StringUtil.serialize(values.get(i)), strCharset));
            }
            jedis.mset((String[]) s.toArray(new String[0]));
        } catch (Exception e) {
            logger.error("mset失败[" + keyPrefix + "]：" + e.getMessage(), e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("mset失败[" + keyPrefix + "]：" + e.getMessage(), e);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }

    }

    @Override
    public List<String> mget(String keyPrefix, List<String> keys) {
        JedisPool pool = choosePool(keyPrefix);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = pool.getResource();
            jedis.connect();
            List<String> s = new ArrayList<String>();
            for (String key : keys) {
                s.add(keyPrefix + key);
            }
            List<String> os = jedis.mget((String[]) s.toArray(new String[0]));
            return os;
        } catch (Exception e) {
            e.printStackTrace();
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            return null;
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    @Override
    public void del(String keyPrefix, String key) {
        JedisPool pool = choosePool(keyPrefix + key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = pool.getResource();
            jedis.connect();
            jedis.del(keyPrefix + key);
        } catch (Exception e) {
            e.printStackTrace();
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    /**
     * 获取自动扫描标记字段，isChecking。
     */
    @Override
    public boolean getIsCheckingOfAutomatedLoanService() {
        try {
            String key = Constants.REDIS_KEY_IS_CHECKING;
            String value = get(key);
            if (StringUtils.isEmpty(value)) {
                return false;
            }
            if ("1".equals(value)) {
                return true;
            } else if ("0".equals(value)) {
                return false;
            }
        } catch (Exception e) {
            logger.error("Exception from RedisServiceImpl - isCheckingOfAutomatedLoanService. ", e);
            return false;
        }
        return false;
    }

    /**
     * 不序列化设置Hash结构的值
     *
     * @param key
     * @param field
     * @param value
     */
    @Override
    public void hsetNoSerialize(String key, String field, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(field) || StringUtils.isEmpty(value)) {
            return;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return;
            }
            jedis.hset(key, field, value);
        } catch (Exception e) {
            logger.error("设置失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    /**
     * 不序列化获取Hash结构的值
     *
     * @param key
     * @param field
     * @return
     */
    @Override
    public String hgetNoSerialize(String key, String field) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(field)) {
            return null;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            return jedis.hget(key, field);
        } catch (Exception e) {
            logger.error("获取失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return null;
    }

    /**
     * 删除hash的一个值
     *
     * @param key
     * @param field
     */
    @Override
    public Long hdel(String key, String field) {
        return hdel(key, new String[]{field});
    }

    /**
     * 删除hash的多个值
     *
     * @param key
     * @param fields
     */
    @Override
    public Long hdel(String key, String[] fields) {
        if (StringUtil.isEmpty(key) || StringUtil.isEmpty(fields)) {
            return null;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            return jedis.hdel(key, fields);
        } catch (Exception e) {
            logger.error("删除失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return null;
    }

    @Override
    public Set<String> keys(String pattern) {
        JedisPool pool = choosePool(pattern);
        Jedis jedis = null;
        boolean connectionBroken = false;

        Set<String> set = null;
        try {
            jedis = pool.getResource();
            jedis.connect();
            set = jedis.keys(pattern);
        } catch (Exception e) {
            e.printStackTrace();
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return set;
    }

    @Override
    public void delSet(Set<String> set) {
        if (null == set || set.size() == 0) {
            return;
        }
        JedisPool pool = choosePool(set.iterator().next());
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return;
            }
            for (String str : set) {
                jedis.del(str);
            }
        } catch (Exception e) {
            logger.error("删除失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }

    }

    @Override
    public Long ttl(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            return jedis.ttl(key);
        } catch (Exception e) {
            logger.error("获取失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return null;
    }

    /**
     * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除
     * set方法会覆盖生存时间, incr不会
     *
     * @param key
     * @param seconds
     * @return
     * @throws RedisConnectException
     */
    @Override
    public boolean expire(String key, int seconds) throws RedisConnectException {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        long result = 0;
        try {
            jedis = connect(pool);
            if (jedis == null) {
                return false;
            }
            result = jedis.expire(key, seconds);
        } catch (RedisConnectException e) {
            logger.error("redis设置值失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("获取redis链接失败");
        } catch (Exception e) {
            logger.error("redis设置值失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("设置redis value失败，未知异常");
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        // 设置成功
        if (result == 1) {
            return true;
        }
        // 该key已经被其他线程设置值
        return false;
    }

    @Override
    public boolean isExist(String key) throws RedisConnectException {
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return false;
            }
            return jedis.exists(key);
        } catch (Exception e) {
            logger.info("redis连接失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("redis连接失败: " + e.getMessage(), e);
        } finally {
            JedisUtils.closeResource(jedis, connectionBroken, pool);
        }
    }

    @Override
    public String lindex(String key, int index) throws RedisConnectException {
        if (StringUtils.isEmpty(key)) {
            return "";
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;
        try {
            jedis = connect(pool);
            if (jedis == null) {
                return "";
            }
            return jedis.lindex(key, index);
        } catch (RedisConnectException e) {
            logger.error("连接redis失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("连接redis失败：" + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("取redis列表值失败[key=" + key + ",index=" + index + "]：" + e.getMessage(), e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("取redis列表值失败[key=" + key + ",index=" + index + "]：" + e.getMessage(), e);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    @Override
    public long lpush(String key, String val) throws RedisConnectException {
        if (StringUtils.isEmpty(key)) {
            return -1;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;
        try {
            jedis = connect(pool);
            if (jedis == null) {
                return -1;
            }
            return jedis.lpush(key, val);
        } catch (RedisConnectException e) {
            logger.error("连接redis失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("连接redis失败：" + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("lpush失败[" + key + ":" + val + "]：" + e.getMessage(), e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("lpush失败[" + key + ":" + val + "]：" + e.getMessage(), e);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }


    @Override
    public long rpush(String key, String val) throws RedisConnectException {
        if (StringUtils.isEmpty(key)) {
            return -1;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;
        try {
            jedis = connect(pool);
            if (jedis == null) {
                return -1;
            }
            return jedis.rpush(key, val);
        } catch (RedisConnectException e) {
            logger.error("连接redis失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("连接redis失败：" + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("rpush失败[" + key + ":" + val + "]：" + e.getMessage(), e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("rpush失败[" + key + ":" + val + "]：" + e.getMessage(), e);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    @Override
    public String rpop(String key) throws RedisConnectException {
        if (StringUtils.isEmpty(key)) {
            return "";
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;
        try {
            jedis = connect(pool);
            if (jedis == null) {
                return "";
            }
            return jedis.rpop(key);
        } catch (RedisConnectException e) {
            logger.error("连接redis失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("连接redis失败：" + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("RPOP失败[" + key + "]：" + e.getMessage(), e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("RPOP失败[" + key + "]：" + e.getMessage(), e);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    @Override
    public long llen(String key) throws RedisConnectException {
        if (StringUtils.isEmpty(key)) {
            return 0;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;
        try {
            jedis = connect(pool);
            if (jedis == null) {
                return 0;
            }
            return jedis.llen(key);
        } catch (RedisConnectException e) {
            logger.error("连接redis失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("连接redis失败：" + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("LLEN失败[" + key + "]：" + e.getMessage(), e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("LLEN失败[" + key + "]：" + e.getMessage(), e);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    @Override
    public Boolean exists(String key) throws RedisConnectException {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;
        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            return jedis.exists(key);
        } catch (RedisConnectException e) {
            logger.error("连接redis失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("连接redis失败：" + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("判断EXISTS失败[" + key + "]：" + e.getMessage(), e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
            throw new RedisConnectException("判断EXISTS失败[" + key + "]：" + e.getMessage(), e);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
    }

    @Override
    public void waitIsCheckingOfAutomatedLoanService(long time, Log log, String task) {
        boolean isChecking = true;
        do {
            isChecking = getIsCheckingOfAutomatedLoanService();
            if (isChecking) {
                log.info("正在进行系统清算，睡眠" + time + "秒后再运行:" + task);
                try {
                    Thread.sleep(time * 1000);
                } catch (InterruptedException e) {
                    log.error("interrupted", e);
                    e.printStackTrace();
                }
            }
        } while (isChecking);
    }


    /**
     * 无key设置
     * 将 key 的值设为 value ，当且仅当 key 不存在。
     * 若给定的 key 已经存在，则 SETNX 不做任何动作
     * 把并发交给redis
     * 有助于减少redis事物代码
     *
     * @param key
     * @param value
     * @return true     等价于set
     * false    有key值
     */
    @Override
    public boolean setnx(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return false;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return false;
            }
            return jedis.setnx(key, value) == 1;
        } catch (Exception e) {
            logger.error("设置失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return false;
    }

    @Override
    public String type(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            return jedis.type(key);
        } catch (Exception e) {
            logger.error("获取失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return null;
    }

    /**
     * 返回哈希表 key 中域的数量
     *
     * @param key
     * @return
     */
    @Override
    public Long hlen(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            return jedis.hlen(key);
        } catch (Exception e) {
            logger.error("获取失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return null;
    }

    /**
     * 返回哈希表 key 中的所有域
     *
     * @param key
     * @return
     */
    @Override
    public Set<String> hkeys(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            return jedis.hkeys(key);
        } catch (Exception e) {
            logger.error("获取失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return null;
    }

    /**
     * 检查一个Set中是否存在member成员
     *
     * @param key    Set的Key
     * @param member 成员
     * @return true:存在  false:不存在
     */
    @Override
    public boolean sismember(String key, String member) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return false;
            }
            return jedis.sismember(key, member);
        } catch (Exception e) {
            logger.error("获取失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return false;
    }

    /**
     * 返回Set中全部数据
     *
     * @param key Set的Key
     * @return
     */
    @Override
    public Set<String> smembers(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            return jedis.smembers(key);
        } catch (Exception e) {
            logger.error("获取失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return null;
    }

    /**
     * 返回关于 Redis 服务器的各种信息和统计数值
     *
     * @return
     */
    @Override
    public String info() {
        JedisPool pool = choosePool("");
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            return jedis.info();
        } catch (Exception e) {
            logger.error("获取失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return null;
    }

    /**
     * 设置异常
     */
    @Override
    public void setException(String message) {
        if (SupervisionConfig.ESCROW_DEV_MODE) {
            String errorPercent = get(Constants.ESCROW_TEST_ERROR_PERCENT);
            if (StringUtil.isDigital(errorPercent)) {
                Random random = new Random();
                int number = random.nextInt(100);
                if (Integer.parseInt(errorPercent) > number) {
                    throw new RuntimeException("test-exception:" + message);
                }
            }
        }
    }

    /**
     * 设置每个业务的异常测试代码
     */
    @Override
    public void setEachBizException(String redisKey, String errorMessage) {
        if (SupervisionConfig.ESCROW_DEV_MODE) {
            String errorPercent = hgetNoSerialize(Constants.ESCROW_TEST_ERROR_PERCENT, redisKey);
            if (StringUtil.isDigital(errorPercent)) {
                Random random = new Random();
                int number = random.nextInt(100);
                if (Integer.parseInt(errorPercent) > number) {
                    throw new RuntimeException("TEST_ERROR_PERCENT:" + redisKey + ":" + errorMessage);
                }
            }
        }
    }

    @Override
    public Long incr(String key) {
        JedisPool pool = choosePool(key);
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            return jedis.incr(key);
        } catch (Exception e) {
            logger.error("设置失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return null;
    }

    /**
     * 往set中添加值
     *
     * @param key    键
     * @param values 值数组
     * @return 操作成功数量
     */
    @Override
    public Long sadd(String key, String[] values) {
        if (StringUtils.isEmpty(key) || null == values) {
            return null;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            return jedis.sadd(key, values);
        } catch (Exception e) {
            logger.error("往set中添加值失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return 0L;
    }

    /**
     * 删除set中指定值
     *
     * @param key    键
     * @param values 值数组
     * @return 操作成功数量
     */
    @Override
    public Long srem(String key, String[] values) {
        if (StringUtils.isEmpty(key) || null == values) {
            return null;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            return jedis.srem(key, values);
        } catch (Exception e) {
            logger.error("删除set中指定值失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return 0L;
    }

    @Override
    public Boolean pipeSAdd(final String key, final Set<String> values) {
        if (StringUtils.isEmpty(key) || null == values) {
            return null;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return null;
            }
            Pipeline pipeline = jedis.pipelined();
            for (String value : values) {
                pipeline.sadd(key, value);
            }
            pipeline.sync();
            return true;
        } catch (Exception e) {
            logger.error("往set中添加值失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return false;
    }

    /**
     * setnx和expire在一个事务
     *
     * @param key
     * @param value
     * @param expire
     * @return 成功或失败
     */
    @Override
    public boolean setnxAndExpire(final String key, final String value, final int expire) {
        // 累加某个标的在Redis中的的投标金额,使用Lua脚本来完成更新操作,目的是保证操作的原子性
        // 返回3种结果:1-累加成功后,标的已经完成的金额 2-insufficient(投资金额累加后,超出了标的总金额) 3-missvalue(不存在该标的投资金额数据)
        final String cad;
        try {
            cad = redisManager.loadScript("setnx_and_expire");
        } catch (IOException ioe) {
            throw new RedisException(RedisException.Action.LOAD_SCRIPT.toString(), ioe);
        }
        try {
            Object result = redisManager.request(new RedisCallback<Object>() {
                @Override
                public Object doInRequest(Jedis jedis) {
                    jedis.scriptLoad(cad);
                    return jedis.eval(cad, 1, key, value, String.valueOf(expire));
                }
            });
            logger.info(Thread.currentThread().getName() + " setnxAndExpire key:" + key + " value:" + value + " expire:" + expire + " result:" + result);
            if (RedisException.Action.EXPIRE_FAILED.name().equals(result)) {
                return false;
            }
            return true;
        } catch (RuntimeException re) {
            throw new RedisException(RedisException.Action.EXECUTE_SCRIPT.toString(), re);
        }
    }

    @Override
    public boolean setnx(String key, String value, int expire) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return false;
        }
        JedisPool pool = choosePool(key);
        Jedis jedis = null;
        boolean connectionBroken = false;

        try {
            jedis = connect(pool);
            if (jedis == null) {
                return false;
            }
            jedis.expire(key, expire);
            Long result = jedis.setnx(key, value);
            return result == 1;
        } catch (Exception e) {
            logger.error("设置失败：", e);
            connectionBroken = JedisUtils.handleJedisException(e, pool);
        } finally {
            if (jedis != null) {
                JedisUtils.closeResource(jedis, connectionBroken, pool);
            }
        }
        return false;
    }

}
