package org.xxz.framework.shiro.entity;

/**
 * 权限
 * @author xxz
 */
public class Permission implements java.io.Serializable {
    
    private static final long serialVersionUID = -4610884868123157542L;
    
    private String token;
    private String url;
    private String description;
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }

}
