package xyz.ccdescipline.Config.RedisConfig;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import xyz.ccdescipline.VO.RedisVO.Login.LoginRedisValue;

@SpringBootConfiguration
public class RedisTemplateConfig {
    @Bean
    public RedisTemplate< BizKey,LoginRedisValue> loginRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return InitRedisTemplate(redisConnectionFactory,LoginRedisValue.class);
    }

    @Bean
    public RedisTemplate<BizKey, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<BizKey, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // key 使用自定义 BizKeySerializer
        redisTemplate.setKeySerializer(new BizKeySerializer());
        redisTemplate.setHashKeySerializer(new BizKeySerializer());

        // value 使用 Jackson 序列化
        Jackson2JsonRedisSerializer<Object> jacksonSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.activateDefaultTyping(
//                LaissezFaireSubTypeValidator.instance,
//                ObjectMapper.DefaultTyping.NON_FINAL,
//                JsonTypeInfo.As.PROPERTY
//        );
//        jacksonSerializer.setObjectMapper(objectMapper);

        redisTemplate.setValueSerializer(jacksonSerializer);
        redisTemplate.setHashValueSerializer(jacksonSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    private <Value> RedisTemplate<BizKey, Value> InitRedisTemplate(
            RedisConnectionFactory redisConnectionFactory,
            Class<Value> valueClass) {

        RedisTemplate<BizKey, Value> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // key 使用自定义 BizKeySerializer
        redisTemplate.setKeySerializer(new BizKeySerializer());
        redisTemplate.setHashKeySerializer(new BizKeySerializer());

        // Value 用 Jackson
        Jackson2JsonRedisSerializer<Value> valueSerializer =
                new Jackson2JsonRedisSerializer<>(valueClass);

//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.activateDefaultTyping(
//                LaissezFaireSubTypeValidator.instance,
//                ObjectMapper.DefaultTyping.NON_FINAL,
//                JsonTypeInfo.As.PROPERTY
//        );
//        valueSerializer.setObjectMapper(objectMapper);

        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
