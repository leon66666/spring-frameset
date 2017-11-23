package wangzhongqiu.spring.redis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Redis {
    /**
     * 追加
     *
     * @param key
     * @param value
     * @return 追加后字符串的长度
     */
    long append(String key, String value);

    /**
     * 删除
     *
     * @param key
     * @return
     */
    long del(String key);

    /**
     * 获取
     *
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 根据key和field获取value
     * @param key
     * @param field
     * @return
     */
    String hget(String key, String field);

    /**
     * 设置
     *
     * @param key
     * @param value
     * @return
     */
    String set(String key, String value);

    /**
     * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)
     *
     * @param key
     * @param value
     * @param expireSeconds
     * @return
     */
    String setex(String key, String value, int expireSeconds);

    /**
     * Redis中的lindex命令, 从List中获取某个Index的值
     *
     * @param key 列表的Key
     * @param index 索引值,如果是正数,从左向右数;如果是负数,从右向左数
     * @return 返回值
     */
    String lindex(String key, int index) throws RedisConnectException;

    /**
     * Redis中的lpush命令,从左向右往List中放数据
     * @param key 列表的Key
     * @param val
     * @return
     * @throws RedisConnectException
     */
    long lpush(String key, String val) throws RedisConnectException;

    /**
     * Redis中的rpush命令, 从右向左往List中放数据
     * @param key
     * @param val
     */
    long rpush(String key, String val) throws RedisConnectException;

    /**
     * Redis中的rpop命令,从右往左从List中取数据,取出数据后,List中的数据删掉
     * @param key 列表的Key
     * @return
     * @throws RedisConnectException
     */
    String rpop(String key) throws RedisConnectException;

    /**
     * 根据参数 count 的值，移除列表中与参数 value 相等的元素
     * @param keyPrefix
     * @param key
     * @param count
     * @param value
     * @return 被移除元素的数量
     * @throws RedisConnectException
     */
    Long lrem(String keyPrefix, String key, long count, String value) throws RedisConnectException;

    /**
     * 检查一个List中元素的个数
     * @param key
     * @return
     * @throws RedisConnectException
     */
    long llen(String key) throws RedisConnectException;

    void mset(String keyPrefix, List<String> keys, List<Serializable> values) throws RedisConnectException;

    List<String> mget(String keyPrefix, List<String> keys);

    void lset(String keyPrefix, String key, List<Serializable> values) throws RedisConnectException;

    List<String> lrange(String keyPrefix, String key, int start, int end);

    Map<String, String> hgetAll(String keyPrefix, String key);

    void del(String keyPrefix, String key);

    Long ttl(String key);
 /**
     * 不序列化设置Hash结构的值
     *
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, String value);


    /** 获取集合 */
    public Set<String> keys(String pattern);


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
     *         false    有key值
     *
     */
    public boolean setnx(String key, String value);

    /**
     * 查看redis值的类型
     * @return
     */
    public String type(String key);

    /**
     * 返回哈希表 key 中域的数量
     * @param key
     * @return
     */
    public Long hlen(String key);

    /**
     * 返回哈希表 key 中的所有域
     * @param key
     * @return
     */
    public Set<String> hkeys(String key);

    /**
     * 检查一个Set中是否存在member成员
     * @param key Set的Key
     * @param member 成员
     * @return true:存在  false:不存在
     */
    public boolean sismember(String key, String member);

    /**
     * 返回Set中全部数据
     *
     * @param key    Set的Key
     * @return
     */
    public Set<String> smembers(String key);

    /**
     * 返回关于 Redis 服务器的各种信息和统计数值
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
     * 往set中添加值
     *
     * @param key 键
     * @param values 值数组
     * @return 操作成功数量
     */
    public Long sadd(String key, String[] values);

    Boolean pipeSAdd(String key, Set<String> values);

    /**
     * 删除set中指定值
     *
     * @param key 键
     * @param values 值数组
     * @return 操作成功数量
     */
    public Long srem(String key, String[] values);

}
