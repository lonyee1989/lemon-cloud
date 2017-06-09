package cn.lemon.security.exception;

/**
 * 没有权限异常
 * Created by lonyee on 2017/6/2.
 */
public class UnAuthorizationException extends RuntimeException {

    public UnAuthorizationException(String message) {
        super(message);
    }
}
