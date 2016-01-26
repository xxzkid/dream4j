package org.xxz.framework.shiro.service;

import java.util.Collection;

import org.xxz.framework.shiro.entity.LoginAccount;

public interface BusinessManager {

    /**
     * 查询权限
     * @param username 用户名
     * @return
     */
    Collection<String> queryPermissions(String username);

    /**
     * 查询角色
     * @param username
     * @return
     */
    Collection<String> queryRoles(String username);

    /**
     * 获取登录账户
     * @param username
     * @return
     */
    LoginAccount getLoginAccount(String username);


}
