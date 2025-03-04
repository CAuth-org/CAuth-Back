package xyz.ccdescipline.Model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("au_role")  // 表名
public class AuRole {

    @TableId(value = "id", type = IdType.ASSIGN_UUID) // 字段名
    private String id; // 角色ID (UUID)

    @TableField("role_name") // 字段名
    private String roleName; // 角色名称

    @TableField("description") // 字段名
    private String description; // 角色描述

    @TableField("created_at") // 字段名
    private LocalDateTime createdAt; // 创建时间

    @TableField("updated_at") // 字段名
    private LocalDateTime updatedAt; // 更新时间


}