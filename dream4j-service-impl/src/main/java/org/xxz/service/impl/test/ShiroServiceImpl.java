package org.xxz.service.impl.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.xxz.entity.test.vo.LoginForm;
import org.xxz.framework.constant.Constants;
import org.xxz.framework.entity.Result;
import org.xxz.framework.shiro.token.CaptchaUsernamePasswordToken;
import org.xxz.service.test.ShiroService;

@Service
public class ShiroServiceImpl implements ShiroService {
    
    @Override
    public Result login(LoginForm loginForm) {
        Subject subject = SecurityUtils.getSubject();
        
        CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken(loginForm.getUsername(), loginForm.getPassword(), loginForm.getRememberMe(), "", loginForm.getCaptcha());
        
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return new Result().setCode(Constants.LOGIN_ACCOUNT_NOT_FOUND);
        } catch (IncorrectCredentialsException ice) {
            return new Result().setCode(Constants.LOGIN_ACCOUNT_PASSWORD_INVALID);
        } catch (LockedAccountException lae) {
            return new Result().setCode(Constants.LOGIN_ACCOUNT_STATUS_INVALID);
        } catch (AuthenticationException ae) {
            return new Result().setCode(Constants.LOGIN_ACCOUNT_AUTHEN_FAILED);
        }
        
        return new Result().setCode(Constants.OK);
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

}
