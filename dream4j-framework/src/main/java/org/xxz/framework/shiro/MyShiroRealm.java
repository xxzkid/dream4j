package org.xxz.framework.shiro;

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

public class MyShiroRealm extends AuthorizingRealm {
    
    // 用于获取用户信息及用户权限信息的业务接口
    private BusinessManager businessManager;

    /**
     * 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.fromRealm(getName()).iterator().next();
        if ( username != null ) {
            Collection<String> permissions = businessManager.queryPermissions(username);
            Collection<String> roles = businessManager.queryRoles(username);
            if ( permissions != null && !permissions.isEmpty() //
                    && roles != null && !roles.isEmpty() ) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                for ( String permission : permissions ) {
                    info.addStringPermission(permission);
                }
                for ( String role : roles) {
                    info.addRole(role);
                }
                return info;
            }
        }
        return null;
    }

    /**
     * 获取认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        if ( username != null && !"".equals(username) ) {
            LoginAccount account = businessManager.get( username );
            return new SimpleAuthenticationInfo( account.getLoginName(), account.getPassword(), getName() );
        }
        return null;
    }

}
