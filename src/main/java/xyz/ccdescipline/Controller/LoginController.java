package xyz.ccdescipline.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccdescipline.Service.LoginService;
import xyz.ccdescipline.Util.Response;
import xyz.ccdescipline.VO.RedisVO.Login.LoginRedisValue;

import java.util.Objects;

@Log4j2
@AllArgsConstructor
@RestController
public class LoginController {
    public final LoginService loginService;

    @GetMapping("/login/{username}/{password}")
    public Response login(@PathVariable String username,@PathVariable String password) {
        String token = loginService.Login(username,password);
        if(Objects.isNull(token)){
            return Response.error("username or password error");
        }
        log.info("有人登录，是 {}",username);
        return Response.success(token);
    }

    @GetMapping("/auth/{token}")
    public Response Auth(@PathVariable  String token){
        LoginRedisValue tokenInfo = loginService.getTokenInfo(token);

        if(Objects.isNull(tokenInfo)){
            return Response.error("token is null");
        }

        return Response.success(tokenInfo);
    }
}
