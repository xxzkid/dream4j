package org.xxz.framework.shiro.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.xxz.framework.entity.Result;

import com.google.gson.Gson;

/**
 * 用户表单认证
 * 
 * @author xxz
 */
public class UserFormAuthenticationFilter extends FormAuthenticationFilter {

    private Logger log = LoggerFactory.getLogger(UserFormAuthenticationFilter.class);

    public UserFormAuthenticationFilter() {
        log.debug("new UserFormAuthenticationFilter()");
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return super.onAccessDenied(request, response);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (!isAjax(httpServletRequest)) {
            issueSuccessRedirect(request, response);
        } else {
            // 使用 AJAX 登录成功返回的信息
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.println(new Gson().toJson(new Result().setCode(0).setMessage("登录成功")));
            out.flush();
            out.close();
        }
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (!isAjax(httpServletRequest)) {
            // login failed, let request continue back to the login page:
            setFailureAttribute(request, e);
            request.setAttribute("error", e.getMessage());
            return true;
        } else {
            try {
                // 使用 AJAX 登录失败返回的信息
                httpServletResponse.setCharacterEncoding("UTF-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.println(new Result().setCode(-1).setMessage("登录失败"));
                out.flush();
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        UsernamePasswordToken token = (UsernamePasswordToken) createToken(request, response);

        try {
            // doCaptchaValidate( (HttpServletRequest)request, token );

            Subject subject = getSubject(request, response);
            subject.login(token);

            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }

        // 可以提供更细节的登录失败信息
        // throw new LockedAccountException("Locked account"); //帐号锁定
    }

    /**
     * 是否为ajax
     * 
     * @param httpServletRequest
     * @return
     */
    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

}
