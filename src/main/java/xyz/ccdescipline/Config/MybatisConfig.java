package xyz.ccdescipline.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.ccdescipline.Interceptor.PrintSQLInterceptor;

@Configuration
public class MybatisConfig {
    @Bean
    public PrintSQLInterceptor sqlInterceptor() {
        return new PrintSQLInterceptor();
    }
}
