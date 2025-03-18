package xyz.ccdescipline.Interceptor;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.ccdescipline.Constant.ResponseEnum;
import xyz.ccdescipline.Exception.ResponseException;
import xyz.ccdescipline.Service.LoginService;
import xyz.ccdescipline.VO.RedisVO.Login.LoginRedisValue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Component
public class AuthTokenInterceptor implements HandlerInterceptor {
    private final LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        List<String> whitelistURL = Arrays.asList(new String[]{"/login"});
        // 放行 /login 请求
//        if (requestURI.startsWith("/login")) {
//            return true;
//        }
        if(whitelistURL.contains(requestURI)){
            return true;
        }

        // 获取 Authorization 头部
        String token = request.getHeader("Authorization");
        if (token == null) {
            throw new ResponseException(ResponseEnum.AUTH_FAIL);
        }

        // 解析 Token 并检查是否有效
        LoginRedisValue tokenInfo = loginService.getTokenInfo(token);
        if (Objects.isNull(tokenInfo)) {
            loginService.Logout(token);
            throw new ResponseException(ResponseEnum.AUTH_FAIL);
        }

        return true; // 允许请求通过
    }
}