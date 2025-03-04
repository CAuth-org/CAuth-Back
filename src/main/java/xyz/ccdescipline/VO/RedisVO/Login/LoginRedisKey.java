package xyz.ccdescipline.VO.RedisVO.Login;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@Data
public class LoginRedisKey implements Serializable {

    @Serial
    private static final long serialVersionUID = 3290552275352790484L;
    private String token;
}
