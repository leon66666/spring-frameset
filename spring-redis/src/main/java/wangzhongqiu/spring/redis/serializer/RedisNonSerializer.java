package wangzhongqiu.spring.redis.serializer;

public class RedisNonSerializer implements RedisSerializer {

    @Override
    public Object deserialize(String s) {
        return s;
    }

    @Override
    public String serialize(Object o) {
        if (!(o instanceof String)) {
            throw new RuntimeException("Failed to serialize non-String object");
        }
        return (String) o;
    }

}
