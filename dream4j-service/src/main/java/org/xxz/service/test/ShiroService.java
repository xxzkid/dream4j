package org.xxz.service.test;

import org.xxz.entity.test.vo.LoginForm;
import org.xxz.framework.entity.Result;

public interface ShiroService {
    
    Result login(LoginForm loginForm);

    void logout();

}
