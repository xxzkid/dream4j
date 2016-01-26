package org.xxz.framework.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 验证码令牌
 * 
 * @author xxz
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

    private static final long serialVersionUID = -3705638016782015194L;

    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public CaptchaUsernamePasswordToken(String username, String password, boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

}
