package org.xxz.framework.shiro.domain;

import java.util.Collection;

import org.xxz.framework.shiro.entity.LoginAccount;

public interface BusinessApi {

    Collection<String> queryPermissions(String username);

    Collection<String> queryRoles(String username);

    LoginAccount getLoginAccount(String username);

}
