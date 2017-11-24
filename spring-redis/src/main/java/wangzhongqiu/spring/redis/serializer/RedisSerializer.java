package wangzhongqiu.spring.redis.serializer;

public interface RedisSerializer {
    
    public Object deserialize(String s);
    
    public String serialize(Object o);
    
}
