package xyz.ccdescipline.Config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.ccdescipline.Interceptor.AuthTokenInterceptor;

@AllArgsConstructor
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    private final AuthTokenInterceptor authTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authTokenInterceptor)
                .addPathPatterns("/**"); // 拦截所有请求
    }
}
