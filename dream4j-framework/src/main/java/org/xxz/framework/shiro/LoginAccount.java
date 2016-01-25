package org.xxz.framework.shiro;

import java.util.Date;

/**
 * 登录账户
 * <p>title: LoginAccount</p>
 * <p>description: </p>
 * @author xxz
 * @date 2016年1月25日 下午1:59:58
 */
public interface LoginAccount extends java.io.Serializable {

    /**
     * 获取登录名
     * @return
     */
    String getLoginName();
    
    /**
     * 获取密码
     * @return 
     */
    String getPassword();
    
    /**
     * 是否启用 
     * @return true 启用 false 禁用
     */
    Boolean isEnabled();

    /**
     * 是否过期
     * @return true 过期 false 未过期
     */
    Boolean isExpired();
    
    /**
     * 创建如期
     * @return
     */
    Date getCreateDate();

}
