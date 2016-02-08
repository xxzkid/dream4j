package org.xxz.framework.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ControllerLogger {

    private final static Logger log = LoggerFactory.getLogger(ControllerLogger.class);

    @AfterReturning(pointcut="execution(* org.xxz.web..*.*(..)) and args(request, response, ..)", returning="retVal")
    public void log(JoinPoint jp, HttpServletRequest request, HttpServletResponse response, Object retVal) {
        log.info("log...." + retVal);
    }

}
