package xyz.ccdescipline.Service;

import cn.hutool.crypto.digest.DigestUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xyz.ccdescipline.Config.RedisConfig.BizKey;
import xyz.ccdescipline.Config.RedisConfig.BizTypeEnum;
import xyz.ccdescipline.Constant.ResponseEnum;
import xyz.ccdescipline.DTO.Login.reqLogin;
import xyz.ccdescipline.DTO.Login.resLogin;
import xyz.ccdescipline.Exception.ResponseException;
import xyz.ccdescipline.Mapper.AuUserInfoMapper;
import xyz.ccdescipline.Model.AuUserInfo;
import xyz.ccdescipline.Util.Response;
import xyz.ccdescipline.Util.TokenGenerator;
import xyz.ccdescipline.VO.RedisVO.Login.LoginRedisValue;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {

    @Value("${CAuth.Login-ExpireTime}")
    private int ExpireTime;

    private final RedisTemplate< BizKey,LoginRedisValue> loginRedisTemplate;
    private final AuUserInfoMapper auUserInfoMapper;

    public Response<resLogin> Login(reqLogin login) throws ResponseException {
        AuUserInfo auUserInfo = auUserInfoMapper.selectUserByUsername(login.getUsername());
        if(Objects.isNull(auUserInfo)){
            throw new ResponseException(ResponseEnum.AUTH_FAIL);
        }

        if(!auUserInfo.getPassword().equals( DigestUtil.md5Hex(login.getPassword() + auUserInfo.getSalt()) )){
            throw new ResponseException(ResponseEnum.AUTH_FAIL);
        }


//        String token = UUID.randomUUID().toString();
        String token = TokenGenerator.generateToken(50);
        BizKey bizKeyLogin = new BizKey(BizTypeEnum.LOGIN_SESSSION, token);
        loginRedisTemplate.opsForValue().set(
                bizKeyLogin,new LoginRedisValue(auUserInfo.getId(),auUserInfo.getRole().getRoleName()),
                ExpireTime, TimeUnit.SECONDS
        );

        return Response.success(new resLogin(token,auUserInfo.getRole().getRoleName()));
    }

    public LoginRedisValue getTokenInfo(String token){
        BizKey bizKeyLogin = new BizKey(BizTypeEnum.LOGIN_SESSSION, token);
        return loginRedisTemplate.opsForValue().get(bizKeyLogin);
    }

    public Response<String> Logout(String token) {
        BizKey bizKeyLogin = new BizKey(BizTypeEnum.LOGIN_SESSSION, token);
        return Boolean.TRUE.equals(loginRedisTemplate.delete(bizKeyLogin))
                ? Response.success("logout success")
                : Response.error("logout fail");
    }
}
