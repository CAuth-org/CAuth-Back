package xyz.ccdescipline.Constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseEnum {
    NO_AUTH(501,"no auth"),
    AUTH_FAIL(502,"auth fail");

    private final int status;
    private final String message;
}
