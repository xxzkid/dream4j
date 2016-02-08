package org.xxz.dao.test;

import org.springframework.stereotype.Repository;
import org.xxz.framework.shiro.entity.LoginAccount;

@Repository
public interface UserTestDao {
    
    int addUser(LoginAccount loginAccount);

}
