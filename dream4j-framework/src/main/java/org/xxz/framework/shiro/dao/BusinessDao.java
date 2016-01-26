package org.xxz.framework.shiro.dao;

import java.util.Collection;

import org.springframework.stereotype.Repository;
import org.xxz.framework.shiro.entity.LoginAccount;

@Repository
public interface BusinessDao {

    Collection<String> queryPermissions(String username);

    Collection<String> queryRoles(String username);

    LoginAccount getLoginAccount(String username);

}
