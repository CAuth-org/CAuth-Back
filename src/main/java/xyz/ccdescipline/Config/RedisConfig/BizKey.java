package xyz.ccdescipline.Config.RedisConfig;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BizKey {
    private BizTypeEnum bizType;  // 业务类型
    private String bizKey;        // 具体业务的 key

    @Override
    public String toString() {
        // 约定序列化规则：USER:12345
        return bizType.getBizName() + ":" + bizKey;
    }
}
