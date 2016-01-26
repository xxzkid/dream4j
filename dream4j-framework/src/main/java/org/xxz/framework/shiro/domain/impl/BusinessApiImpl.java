package org.xxz.framework.shiro.domain.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.xxz.framework.shiro.dao.BusinessDao;
import org.xxz.framework.shiro.domain.BusinessApi;
import org.xxz.framework.shiro.entity.LoginAccount;

@Service
public class BusinessApiImpl implements BusinessApi {
    
    @Resource
    private BusinessDao businessDao;

    @Override
    public Collection<String> queryPermissions(String username) {
        return businessDao.queryPermissions(username);
    }

    @Override
    public Collection<String> queryRoles(String username) {
        return businessDao.queryRoles(username);
    }

    @Override
    public LoginAccount getLoginAccount(String username) {
        return businessDao.getLoginAccount(username);
    }

}
