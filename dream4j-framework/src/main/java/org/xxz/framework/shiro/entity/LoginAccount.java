package org.xxz.framework.shiro.entity;

import java.util.Date;

import lombok.Data;

/**
 * 登录账户
 * @author xxz
 */
@Data
public class LoginAccount implements java.io.Serializable {

    private static final long serialVersionUID = -2518905276772497871L;
    
    private String id;
    private String loginName;
    private String password;
    private boolean enabled;
    private boolean expired;
    private Date createDate;

}
