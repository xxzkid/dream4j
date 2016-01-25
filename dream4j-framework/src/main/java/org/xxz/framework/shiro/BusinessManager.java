package org.xxz.framework.shiro;

import java.util.Collection;

public interface BusinessManager {

    /**
     * 查询权限
     * @param username 用户名
     * @return
     */
    Collection<String> queryPermissions(String username);

    /**
     * 获取登录账户
     * @param username
     * @return
     */
    LoginAccount get(String username);

}
