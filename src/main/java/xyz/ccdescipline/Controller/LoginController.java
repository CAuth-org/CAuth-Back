package xyz.ccdescipline.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ccdescipline.DTO.Login.reqLogin;
import xyz.ccdescipline.Service.LoginService;
import xyz.ccdescipline.Util.Response;
import xyz.ccdescipline.VO.RedisVO.Login.LoginRedisValue;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Log4j2
@AllArgsConstructor
@RestController
public class LoginController {
    public final LoginService loginService;

    @PostMapping("/login")
    public Response login( reqLogin reqLogin) {
        return loginService.Login(reqLogin);
    }

    @PostMapping("/logout")
    public Response logout(HttpServletRequest request){
        return loginService.Logout(request.getHeader("Authorization"));
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
