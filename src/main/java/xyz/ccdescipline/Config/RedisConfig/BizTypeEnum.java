package xyz.ccdescipline.Config.RedisConfig;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BizTypeEnum {
    LOGIN_SESSSION("loginSession"),
    CAPTCHA("captcha");

    private final String BizName;
}
