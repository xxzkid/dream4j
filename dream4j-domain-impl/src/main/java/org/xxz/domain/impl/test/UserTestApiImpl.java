package org.xxz.domain.impl.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.xxz.common.util.IDUtil;
import org.xxz.dao.test.UserTestDao;
import org.xxz.domain.test.UserTestApi;
import org.xxz.framework.shiro.entity.LoginAccount;

@Service
public class UserTestApiImpl implements UserTestApi {

    @Resource
    private UserTestDao userTestDao;
    
    @Override
    public int addUser(LoginAccount loginAccount) {
        if ( loginAccount == null ) throw new IllegalArgumentException("loginAccount is null");
        loginAccount.setId(IDUtil.uuid());
        return userTestDao.addUser(loginAccount);
    }

}
