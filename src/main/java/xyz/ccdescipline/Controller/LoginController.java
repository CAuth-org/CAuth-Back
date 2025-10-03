package xyz.ccdescipline.Controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import xyz.ccdescipline.DTO.Login.reqLogin;
import xyz.ccdescipline.Exception.ResponseException;
import xyz.ccdescipline.Service.LoginService;
import xyz.ccdescipline.Util.Response;
import xyz.ccdescipline.VO.RedisVO.Login.LoginRedisValue;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Log4j2
@RequiredArgsConstructor
@RestController
public class LoginController {
    public final LoginService loginService;

    @PostMapping("/login")
    public Response login(@RequestBody reqLogin reqLogin) throws ResponseException {
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
