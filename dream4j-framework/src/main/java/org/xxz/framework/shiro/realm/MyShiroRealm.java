package org.xxz.framework.shiro.realm;

import java.util.Collection;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xxz.framework.constant.Constants;
import org.xxz.framework.shiro.entity.LoginAccount;
import org.xxz.framework.shiro.service.BusinessManager;

public class MyShiroRealm extends AuthorizingRealm {
    
    private final Logger log = LoggerFactory.getLogger(MyShiroRealm.class);
    
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
            
            Session session = SecurityUtils.getSubject().getSession();
            // 移除session user
            session.removeAttribute(Constants.SESSION_USER_KEY);
            
            // 用户是否存在
            if ( account == null ) {
                log.error( "account is not exists" );
                throw new UnknownAccountException( "account is not exists" );
            }
            
            // 密码校验
            if ( !token.getPassword().equals(account.getPassword()) ) {
                log.error( "account password invalid" );
                throw new IncorrectCredentialsException( "account password invalid" );
            }
            
            // 用户状态校验
            if ( !account.isEnabled() ) {
                log.error( "account is locked" );
                throw new LockedAccountException( "account is locked" );
            }
            
            // 检查用户是否过期
            if ( !account.isExpired() ) {
                log.error( "account is expired" );
                throw new ExpiredCredentialsException( "account is expired" );
            }
            
            // session 存入用户
            session.setAttribute( Constants.SESSION_USER_KEY, account );
            
            return new SimpleAuthenticationInfo( account.getLoginName(), account.getPassword(), getName() );
        }
        return null;
    }

}
