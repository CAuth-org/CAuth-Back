package xyz.ccdescipline.VO.RedisVO.Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRedisValue implements Serializable {
    @Serial
    private static final long serialVersionUID = -8874698070790426467L;

    private String uid;

    private String roleName;
}
