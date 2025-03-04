package xyz.ccdescipline.Config;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import xyz.ccdescipline.Util.Response;

@Log4j2
@ControllerAdvice
public class ExceptionConfig {



    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> handleGeneralException(Exception ex) {
        // 记录异常信息到日志中
        log.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        // 处理其他异常，返回一个通用的错误响应
        Response<String> response = Response.error(500, ex.getMessage());
        // 处理其他未被捕获的异常
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}