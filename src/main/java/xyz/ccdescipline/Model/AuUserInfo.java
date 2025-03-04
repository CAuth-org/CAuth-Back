package xyz.ccdescipline.Model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("au_userinfo")  // 表名
public class AuUserInfo {

    @TableId(value = "id", type = IdType.ASSIGN_UUID) // 字段名
    private String id; // 用户ID（UUID）

    @TableField("username") // 字段名
    private String username; // 用户名（唯一）

    @TableField("password") // 字段名
    private String password; // 密码（md5(password + salt)）

    @TableField("salt") // 字段名
    private String salt; // 密码加密的盐值（UUID）

    @TableField("parent") // 字段名
    private String parent; // 上级用户ID，引用 `au_userinfo` 表的 `id`

    @TableField("created_at") // 字段名
    private LocalDateTime createdAt; // 创建时间

    @TableField("updated_at") // 字段名
    private LocalDateTime updatedAt; // 更新时间

    @TableField("role_id") // 字段名
    private String roleId; // 用户角色，关联 `au_role` 表

    // 直接嵌套 AuRole 对象，映射用户角色
    @TableField(exist = false) // 不会映射到数据库字段
    private AuRole role; // 用户角色，关联 au_role 表

}