package xyz.ccdescipline.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE}) // 可用于方法或类
@Retention(RetentionPolicy.RUNTIME) // 运行时可用
@Repeatable(Roles.class)  // 允许重复使用，并指定容器
public @interface Role {
    String value(); // 角色名称
}
