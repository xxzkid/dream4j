package org.xxz.framework.shiro.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.xxz.framework.shiro.domain.BusinessApi;
import org.xxz.framework.shiro.entity.LoginAccount;
import org.xxz.framework.shiro.service.BusinessManager;

/**
 * businessManager
 * @author xxz
 */
@Transactional
public class BusinessManagerImpl implements BusinessManager {
    
    @Resource
    private BusinessApi businessApi;

    @Override
    public Collection<String> queryPermissions(String username) {
        return businessApi.queryPermissions(username);
    }

    @Override
    public Collection<String> queryRoles(String username) {
        return businessApi.queryRoles(username);
    }

    @Override
    public LoginAccount getLoginAccount(String username) {
        return businessApi.getLoginAccount(username);
    }

}
