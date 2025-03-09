package xyz.ccdescipline.AOP;

import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import xyz.ccdescipline.Service.LoginService;
import xyz.ccdescipline.VO.RedisVO.Login.LoginRedisValue;
import xyz.ccdescipline.annotation.Role;
import xyz.ccdescipline.annotation.Roles;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Aspect
@Component
@AllArgsConstructor
public class RoleAspect {
    private final HttpServletRequest request;
    private final LoginService loginService;

    public Set<String> getRoles(AnnotatedElement accessibleObject){

        Set<String> requiredRoles = new HashSet<>();
        // 解析 @Role
        if (accessibleObject.isAnnotationPresent(Role.class)) {
            requiredRoles.add(accessibleObject.getAnnotation(Role.class).value().getRoleName());
        }

        // 解析 @Roles
        if (accessibleObject.isAnnotationPresent(Roles.class)) {
            Role[] roles = accessibleObject.getAnnotation(Roles.class).value();
            requiredRoles.addAll(Arrays.stream(roles).map(role -> role.value().getRoleName()).toList());
        }

        return requiredRoles;
    }


    @Around("@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.GetMapping) ")
    public Object checkRole(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();

        // 获取当前访问的方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        if(targetClass == null || method == null){
            return joinPoint.proceed();
        }

        Set<String> requiredRoles = new HashSet<>();

        requiredRoles.addAll(getRoles(method));
        requiredRoles.addAll(getRoles(targetClass));


        // 如果没有  直接放行
        if (requiredRoles.isEmpty()) {
            return joinPoint.proceed();
        }

        // 获取 token
        String token = request.getHeader("Authorization");
        if (token == null ) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token missing");
        }

        // 解析角色
        LoginRedisValue tokenInfo = loginService.getTokenInfo(token);
        if (tokenInfo.getRoleName() == null || !requiredRoles.contains(tokenInfo.getRoleName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
        }

        // 权限通过
        return joinPoint.proceed();
    }
}
