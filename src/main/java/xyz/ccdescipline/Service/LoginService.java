package xyz.ccdescipline.Service;

import cn.hutool.crypto.digest.DigestUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xyz.ccdescipline.DTO.Login.reqLogin;
import xyz.ccdescipline.DTO.Login.resLogin;
import xyz.ccdescipline.Mapper.AuUserInfoMapper;
import xyz.ccdescipline.Model.AuUserInfo;
import xyz.ccdescipline.Util.Response;
import xyz.ccdescipline.VO.RedisVO.Login.LoginRedisKey;
import xyz.ccdescipline.VO.RedisVO.Login.LoginRedisValue;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import cn.hutool.crypto.SecureUtil;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {

    @Value("${CAuth.Login-ExpireTime}")
    private int ExpireTime;

    private final RedisTemplate< LoginRedisKey,LoginRedisValue> loginRedisTemplate;
    private final AuUserInfoMapper auUserInfoMapper;

    public Response<resLogin> Login(reqLogin login){
        AuUserInfo auUserInfo = auUserInfoMapper.selectUserByUsername(login.getUsername());
        if(Objects.isNull(auUserInfo)){
            return Response.error("auth is fail");
        }

        if(!auUserInfo.getPassword().equals( DigestUtil.md5Hex(login.getPassword() + auUserInfo.getSalt()) )){
            return Response.error("auth is fail");
        }


        String token = UUID.randomUUID().toString();
        loginRedisTemplate.opsForValue().set(
                new LoginRedisKey(token),new LoginRedisValue(auUserInfo.getId(),auUserInfo.getRole().getRoleName()),
                ExpireTime, TimeUnit.SECONDS
        );

        return Response.success(new resLogin(token,auUserInfo.getRole().getRoleName()));
    }

    public LoginRedisValue getTokenInfo(String token){
        return loginRedisTemplate.opsForValue().get(new LoginRedisKey(token));
    }

    public Response Logout(String authorization) {
        return Boolean.TRUE.equals(loginRedisTemplate.delete(new LoginRedisKey(authorization)))
                ? Response.success("logout success")
                : Response.error("logout fail");
    }
}
