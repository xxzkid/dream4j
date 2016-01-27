package org.xxz.framework.shiro.entity;

import java.util.Date;

/**
 * 登录账户
 * @author xxz
 */
public class LoginAccount implements java.io.Serializable {

    private static final long serialVersionUID = -2518905276772497871L;
    
    private String id;
    private String loginName;
    private String password;
    private boolean enabled;
    private boolean expired;
    private Date createDate;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public boolean getEnabled() {
        return enabled;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isExpired() {
        return expired;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

}
