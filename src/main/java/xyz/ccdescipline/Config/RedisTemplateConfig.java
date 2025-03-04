package xyz.ccdescipline.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import xyz.ccdescipline.VO.RedisVO.Login.LoginRedisKey;
import xyz.ccdescipline.VO.RedisVO.Login.LoginRedisValue;

@SpringBootConfiguration
public class RedisTemplateConfig {
    @Bean
    public RedisTemplate< LoginRedisKey,LoginRedisValue> loginRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return InitRedisTemplate(redisConnectionFactory);
    }

    private  <Key, Value> RedisTemplate<Key, Value> InitRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Key, Value> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 设置 Jackson 反序列化器
        Jackson2JsonRedisSerializer<LoginRedisValue> serializer = new Jackson2JsonRedisSerializer<>(LoginRedisValue.class);
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
        return redisTemplate;
    }

}
