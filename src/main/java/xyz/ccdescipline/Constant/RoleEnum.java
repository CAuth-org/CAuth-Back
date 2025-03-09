package xyz.ccdescipline.Constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {
    ADMIN("admin"),
    USER("user");
    private final String roleName;
}
