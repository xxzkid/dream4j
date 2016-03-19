package org.xxz.framework.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.xxz.framework.constant.Constants;
import org.xxz.framework.shiro.entity.LoginAccount;

@Aspect
public class ControllerLogger {

    private final static Log log = LogFactory.getLog(ControllerLogger.class);

    @AfterReturning(pointcut="execution(* org.xxz.web..*.*(..)) and args(request, response, ..)", returning="retVal")
    public void log(JoinPoint jp, HttpServletRequest request, HttpServletResponse response, Object retVal) {
        Subject subject = SecurityUtils.getSubject();
        String userId = null;
        String loginName = null;
        if(subject != null) {
            LoginAccount account = (LoginAccount) subject.getSession().getAttribute(Constants.SESSION_USER_KEY);
            if( account != null ) {
                userId = account.getId();
                loginName = account.getLoginName();
            }
        }
        log.info("请求方法：" + retVal + "###" + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + "(" + Arrays.toString(jp.getArgs()) + ")" + "请求用户：(" + userId + "," + loginName + "," + this.getIp(request) + ")");
    }
    
    private String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
