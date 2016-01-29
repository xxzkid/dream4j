package org.xxz.web.test;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xxz.common.util.HttpUtil;
import org.xxz.entity.test.vo.LoginForm;
import org.xxz.framework.code.Captcha;
import org.xxz.framework.constant.Constants;
import org.xxz.framework.entity.Result;
import org.xxz.service.test.ShiroService;

@Controller
public class ShiroController {
    
    @Resource
    private ShiroService shiroTestService;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView m = new ModelAndView("login");
        
        Subject subject = SecurityUtils.getSubject();
        // 如果已经登录，则转入登录成功的页面，防止继续登录
        if (subject.isAuthenticated()) {
            m.setViewName("redirect:/test");;
        }
        
        return m;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView dologin( HttpServletRequest request, HttpServletResponse response, @Valid LoginForm loginForm, BindingResult bindingResult ) {
        ModelAndView m = new ModelAndView();
        
        if (bindingResult.hasErrors()) {
            m.addObject(new Result().setCode(Constants.FAILED).setObject(bindingResult.getAllErrors()));
            return m;
        }
        
        // 验证码校验
        if ( !Captcha.validateCaptcha(request, loginForm.getCaptcha()) ) {
            m.addObject( new Result().setCode(Constants.CAPTCHA_ERROR) );
            return m;
        }
        
        loginForm.setIp(HttpUtil.getIp(request));
        Result result = shiroTestService.login(loginForm);
        m.addObject(result);
        
        return m;
    }
    
    @RequiresRoles("admin")
    @RequestMapping(value = "/test")
    public ModelAndView test( HttpServletRequest request, HttpServletResponse response ) {
        ModelAndView m = new ModelAndView("test");
        return m;
    }
    
    @RequestMapping(value = "/unauthorized")
    public ModelAndView unauthorized( HttpServletRequest request, HttpServletResponse response ) {
        ModelAndView m = new ModelAndView("unauthorized");
        return m;
    }
    
    @RequestMapping(value = "/logout")
    @ResponseBody
    public ModelAndView logout( HttpServletRequest request, HttpServletResponse response ) {
        ModelAndView m = new ModelAndView("redirect:/login");
        shiroTestService.logout();
        m.addObject(new Result().setCode(Constants.OK));
        return m;
    }

}
