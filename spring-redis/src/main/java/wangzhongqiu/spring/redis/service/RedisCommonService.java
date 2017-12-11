package wangzhongqiu.spring.redis.service;

import org.apache.commons.logging.Log;
import redis.clients.jedis.Jedis;
import wangzhongqiu.spring.core.exception.RedisConnectException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisCommonService {

    /**
     * 以step的步长自增
     * redis原生的原子操作
     * @param key
     * @param step
     * @return 自增后的值
     * @throws RedisConnectException
     */
    Long incrBy(String key, long step) throws RedisConnectException;
    /**
     * 追加
     *
     * @param key
     * @param value
     * @return 追加后字符串的长度
     */
    public long append(String key, String value);

    /**
     * 删除
     *
     * @param key
     * @return
     */
    public long del(String key);

    /**
     * 获取
     *
     * @param key
     * @return
     */
    public String get(String key);

    /**
     * 根据key和field获取value
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field);

    /**
     * 设置
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value);

    /**
     * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)
     *
     * @param key
     * @param value
     * @param expireSeconds
     * @return
     */
    public String setex(String key, String value, int expireSeconds);

    public String setexNoContinue(String key, String value, int expireSeconds);

    public void setObj(String keyPrefix, String key, Serializable value, int expireSeconds);

    public void setObj(String keyPrefix, String key, Serializable value);

    public Serializable getObj(String keyPrefix, String key);


    /**
     * setStr过期时间永久
     */
    public void setStrAndPersist(String key, String value);

    public void setStr(String keyPrefix, String key, String value, int expireSeconds);

    public String getStr(String keyPrefix, String key);

    public void setList(String keyPrefix, String key, List<String> keys, int expireSeconds);

    public List<String> getList(String keyPrefix, String key);

    /**
     * Redis中的lindex命令, 从List中获取某个Index的值
     *
     * @param key   列表的Key
     * @param index 索引值,如果是正数,从左向右数;如果是负数,从右向左数
     * @return 返回值
     */
    public String lindex(String key, int index) throws RedisConnectException;

    /**
     * Redis中的lpush命令,从左向右往List中放数据
     *
     * @param key 列表的Key
     * @param val
     * @return
     * @throws RedisConnectException
     */
    public long lpush(String key, String val) throws RedisConnectException;

    /**
     * Redis中的rpush命令, 从右向左往List中放数据
     *
     * @param key
     * @param val
     */
    public long rpush(String key, String val) throws RedisConnectException;

    /**
     * Redis中的rpop命令,从右往左从List中取数据,取出数据后,List中的数据删掉
     *
     * @param key 列表的Key
     * @return
     * @throws RedisConnectException
     */
    public String rpop(String key) throws RedisConnectException;

    /**
     * 根据参数 count 的值，移除列表中与参数 value 相等的元素
     *
     * @param keyPrefix
     * @param key
     * @param count
     * @param value
     * @return 被移除元素的数量
     * @throws RedisConnectException
     */
    public Long lrem(String keyPrefix, String key, long count, String value) throws RedisConnectException;

    /**
     * 检查一个List中元素的个数
     *
     * @param key
     * @return
     * @throws RedisConnectException
     */
    public long llen(String key) throws RedisConnectException;

    public void mset(String keyPrefix, List<String> keys, List<Serializable> values) throws RedisConnectException;

    public List<String> mget(String keyPrefix, List<String> keys);

    public void lset(String keyPrefix, String key, List<Serializable> values) throws RedisConnectException;

    public List<String> lrange(String keyPrefix, String key, int start, int end);

    public List<String> lrange(String key, int start, int end);

    public Map<String, String> hgetAll(String keyPrefix, String key);

    public void del(String keyPrefix, String key);

    public Long ttl(String key);

    /**
     * 获取自动扫描标记字段，isChecking。
     */
    public boolean getIsCheckingOfAutomatedLoanService();


    /**
     * 获取自动扫描标记字段isChecking,每次间隔time秒进行重复检测,直到isChecking为false
     *
     * @param time 每次等待的时间,单位:秒
     * @param log  日志
     * @param task 任务名称
     * @return
     */
    public void waitIsCheckingOfAutomatedLoanService(long time, Log log, String task);


    /**
     * 不序列化设置Hash结构的值
     *
     * @param key
     * @param field
     * @param value
     */
    public void hsetNoSerialize(String key, String field, String value);

    /**
     * 不序列化获取Hash结构的值
     *
     * @param key
     * @param field
     * @return
     */
    public String hgetNoSerialize(String key, String field);

    /**
     * 获取集合
     */
    public Set<String> keys(String pattern);

    /**
     * 删除集合
     */
    public void delSet(Set<String> set);

    /**
     * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除
     * set方法会覆盖生存时间, incr不会
     *
     * @param key
     * @param seconds
     * @return
     * @throws RedisConnectException
     */
    public boolean expire(String key, int seconds) throws RedisConnectException;

    /**
     * 检查给定 key 是否存在
     *
     * @param key
     * @return
     * @throws RedisConnectException
     */
    public Boolean exists(String key) throws RedisConnectException;

    /**
     * 删除hash的一个值
     *
     * @param key
     * @param field
     */
    public Long hdel(String key, String field);

    /**
     * 删除hash的一个值
     *
     * @param key
     * @param fields
     * @return
     */
    public Long hdel(String key, String[] fields);

    public boolean isExist(String key) throws RedisConnectException;


    /**
     * 无key设置
     * 把并发交给redis
     * 有助于减少redis事物代码
     *
     * @param key
     * @param value
     * @return true     等价于set
     * false    有key值
     */
    public boolean setnx(String key, String value);

    /**
     * 查看redis值的类型
     *
     * @param key
     * @return
     */
    public String type(String key);

    /**
     * 返回哈希表 key 中域的数量
     *
     * @param key
     * @return
     */
    public Long hlen(String key);

    /**
     * 返回哈希表 key 中的所有域
     *
     * @param key
     * @return
     */
    public Set<String> hkeys(String key);

    /**
     * 检查一个Set中是否存在member成员
     *
     * @param key    Set的Key
     * @param member 成员
     * @return true:存在  false:不存在
     */
    public boolean sismember(String key, String member);

    /**
     * 返回Set中全部数据
     *
     * @param key Set的Key
     * @return
     */
    public Set<String> smembers(String key);

    /**
     * 返回关于 Redis 服务器的各种信息和统计数值
     *
     * @return
     */
    public String info();

    /**
     * key值自增
     *
     * @param key
     * @return
     */
    public Long incr(String key);

    /**
     * 设置异常
     */
    public void setException(String message);

    /**
     * 设置每个业务的异常测试代码
     */
    public void setEachBizException(String redisKey, String errorMessage);

    /**
     * 往set中添加值
     *
     * @param key    键
     * @param values 值数组
     * @return 操作成功数量
     */
    public Long sadd(String key, String[] values);

    Boolean pipeSAdd(String key, Set<String> values);

    /**
     * 删除set中指定值
     *
     * @param key    键
     * @param values 值数组
     * @return 操作成功数量
     */
    public Long srem(String key, String[] values);

    /**
     * setnx和expire在一个事务
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public boolean setnxAndExpire(final String key, final String value, final int expire);


    public boolean setnx(final String key, final String value, final int expire);

    /**
     * Watch回调
     *
     *
     */
    interface WatchCallback {

        String doInWatch(Jedis jedis);

    }

    /**
     * 加锁
     *
     * @param key
     * @param expire
     *            过期时间(单位：秒)
     * @return
     */
    boolean lock(String key, Integer expire);

    /**
     * 解锁
     *
     * @param key
     * @return
     */
    boolean unlock(String key);

    /**
     * 解锁
     * @param key
     * @param expire
     * @return
     */
    public  boolean doLockNoContinue(String key, Integer expire);
}
