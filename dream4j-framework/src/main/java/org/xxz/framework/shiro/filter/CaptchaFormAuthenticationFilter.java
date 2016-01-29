package org.xxz.framework.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.xxz.framework.captcha.Captcha;
import org.xxz.framework.shiro.exception.IncorrectCaptchaException;
import org.xxz.framework.shiro.token.CaptchaUsernamePasswordToken;

public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

    private String captchaParam = Captcha.CAPTCHAT_KEY;

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    // 创建 Token
    protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {

        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);

        return new CaptchaUsernamePasswordToken(username, password, rememberMe, host, captcha);
    }

    // 验证码校验
    protected void doCaptchaValidate(HttpServletRequest request, CaptchaUsernamePasswordToken token) {

        String captcha = (String) request.getSession().getAttribute(Captcha.CAPTCHAT_KEY);

        if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
            throw new IncorrectCaptchaException("验证码错误！");
        }
    }

    // 认证
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        CaptchaUsernamePasswordToken token = createToken(request, response);

        try {
            doCaptchaValidate((HttpServletRequest) request, token);

            Subject subject = getSubject(request, response);
            subject.login(token);

            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }
    
}
