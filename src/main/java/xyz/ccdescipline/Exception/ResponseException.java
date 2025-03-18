package xyz.ccdescipline.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.ccdescipline.Constant.ResponseEnum;

import java.io.Serial;

@Data
@AllArgsConstructor
public class ResponseException extends Exception{

    @Serial
    private static final long serialVersionUID = 1850599186685158172L;
    private final ResponseEnum responseEnum;
}
