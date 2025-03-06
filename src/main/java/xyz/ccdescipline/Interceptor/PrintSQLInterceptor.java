package xyz.ccdescipline.Interceptor;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;
import java.util.StringJoiner;

@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class PrintSQLInterceptor  implements Interceptor {

    @Value("${CAuth.isPrintSQL}")
    private Boolean isPrintSQL;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if(!isPrintSQL){
            return invocation.proceed();
        }

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();

        // 获取 SQL 语句
        String sql = boundSql.getSql();

        // 获取 SQL 参数
        Object parameterObject = boundSql.getParameterObject();
        if (parameterObject != null) {
            MetaObject metaObject = SystemMetaObject.forObject(parameterObject);
            //List<Object> parameterValues = metaObject.getGetterNames().length > 0 ? metaObject.getValue() : List.of(parameterObject);
            List<Object> parameterValues =List.of(parameterObject);

            // 替换 ? 为参数值
            StringJoiner fullSql = new StringJoiner(" ");
            int index = 0;
            for (String part : sql.split("\\?")) {
                fullSql.add(part);
                if (index < parameterValues.size()) {
                    fullSql.add("'" + parameterValues.get(index++) + "'");
                }
            }
            sql = fullSql.toString();
        }

        log.info("完整 SQL: {}" , sql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
