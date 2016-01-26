package org.xxz.web.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    
    @RequestMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView m = new ModelAndView("login");
        return m;
    }
    
    @RequestMapping(value = "/doLogin")
    public ModelAndView doLogin() {
        ModelAndView m = new ModelAndView();
        return m;
    }

}
