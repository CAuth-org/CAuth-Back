package xyz.ccdescipline.Service;

import cn.hutool.crypto.digest.DigestUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xyz.ccdescipline.Mapper.AuUserInfoMapper;
import xyz.ccdescipline.Model.AuUserInfo;
import xyz.ccdescipline.VO.RedisVO.Login.LoginRedisKey;
import xyz.ccdescipline.VO.RedisVO.Login.LoginRedisValue;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import cn.hutool.crypto.SecureUtil;

@RequiredArgsConstructor
@Service
public class LoginService {

    @Value("${CAuth.Login-ExpireTime}")
    private int ExpireTime;

    private final RedisTemplate< LoginRedisKey,LoginRedisValue> loginRedisTemplate;
    private final AuUserInfoMapper auUserInfoMapper;

    public String Login(String username, String password){
        AuUserInfo auUserInfo = auUserInfoMapper.selectUserByUsername(username);
        if(Objects.isNull(auUserInfo)){
            return null;
        }

        if(!auUserInfo.getPassword().equals( DigestUtil.md5Hex(password + auUserInfo.getSalt()) )){
            return null;
        }


        String token = UUID.randomUUID().toString();
        loginRedisTemplate.opsForValue().set(
                new LoginRedisKey(token),new LoginRedisValue(auUserInfo.getId(),auUserInfo.getRole().getRoleName()),
                ExpireTime, TimeUnit.SECONDS
        );

        return token;
    }

    public LoginRedisValue getTokenInfo(String token){
        return loginRedisTemplate.opsForValue().get(new LoginRedisKey(token));
    }
}
