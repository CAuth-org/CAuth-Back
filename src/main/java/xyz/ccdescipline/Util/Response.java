package xyz.ccdescipline.Util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response<T> {
    private int code;       // 状态码
    private String message; // 提示信息
    private T data;         // 返回的数据



    // 静态方法创建 Response 实例
    public static <T> Response<T> success(T data) {
        return new Response<>(200, "Success", data);
    }

    public static <T> Response<T> error(int code, String message) {
        return new Response<>(code, message,null);
    }

    public static <T> Response<T> error(String message) {
        return error(500, message);
    }
}
