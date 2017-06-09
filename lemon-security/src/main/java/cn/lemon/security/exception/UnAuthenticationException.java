package cn.lemon.security.exception;

/**
 * 未授权异常
 * Created by lonyee on 2017/6/2.
 */
public class UnAuthenticationException extends RuntimeException {
    public UnAuthenticationException(String message) {
        super(message);
    }
}
