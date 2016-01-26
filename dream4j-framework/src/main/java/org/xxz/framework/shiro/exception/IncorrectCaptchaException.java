package org.xxz.framework.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 验证码错误异常
 * @author xxz
 */
public class IncorrectCaptchaException extends AuthenticationException {

    private static final long serialVersionUID = 8658838444881598751L;

    public IncorrectCaptchaException() {
        super();
    }

    public IncorrectCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCaptchaException(String message) {
        super(message);
    }

    public IncorrectCaptchaException(Throwable cause) {
        super(cause);
    }

}
