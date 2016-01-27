package org.xxz.framework.shiro.realm;

import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.xxz.framework.shiro.entity.LoginAccount;
import org.xxz.framework.shiro.service.BusinessManager;

public class MyShiroRealm extends AuthorizingRealm {
    
    // 用于获取用户信息及用户权限信息的业务接口
    private BusinessManager businessManager;

    public void setBusinessManager(BusinessManager businessManager) {
        this.businessManager = businessManager;
    }

    /**
     * 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        final String username = (String) principals.getPrimaryPrincipal();
        final Collection<String> permissions = businessManager.queryPermissions( username );
        final Collection<String> roles = businessManager.queryRoles( username );
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

    /**
     * 获取认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        if ( username != null && !"".equals(username) ) {
            LoginAccount account = businessManager.getLoginAccount( username );
            return new SimpleAuthenticationInfo( account.getLoginName(), account.getPassword(), getName() );
        }
        return null;
    }

}
