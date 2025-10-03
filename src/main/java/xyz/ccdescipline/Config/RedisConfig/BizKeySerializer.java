package xyz.ccdescipline.Config.RedisConfig;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.StandardCharsets;


///
/// "bigName:keys" <-> class
public class BizKeySerializer implements RedisSerializer<BizKey> {

    @Override
    public byte[] serialize(BizKey bizKey) throws SerializationException {
        if (bizKey == null) {
            return new byte[0];
        }
        return bizKey.toString().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public BizKey deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        String str = new String(bytes, StandardCharsets.UTF_8);
        String[] parts = str.split(":", 2);
        if (parts.length != 2) {
            throw new SerializationException("Invalid BizKey format: " + str);
        }
        return new BizKey(BizTypeEnum.valueOf(parts[0]), parts[1]);
    }
}