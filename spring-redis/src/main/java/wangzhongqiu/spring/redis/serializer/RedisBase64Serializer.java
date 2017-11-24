package wangzhongqiu.spring.redis.serializer;

import org.apache.geronimo.mail.util.Base64;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;

public class RedisBase64Serializer implements RedisSerializer {

    private Converter<Object, byte[]> serializer = new SerializingConverter();
    
    private Converter<byte[], Object> deserializer = new DeserializingConverter();
    
    public Object deserialize(String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        byte[] byteValue = Base64.decode(value.getBytes());
        return deserializer.convert(byteValue);
    }
    
    public String serialize(Object value) {
        if (value == null) {
            return "";
        }
        byte[] byteValue = serializer.convert(value);
        return new String(Base64.encode(byteValue));
    }
}
