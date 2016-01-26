package org.xxz.framework.shiro.entity;

/**
 * 角色
 * @author xxz
 */
public class Role implements java.io.Serializable {
    
    private static final long serialVersionUID = -9104007696154461683L;
    
    private String name;
    private String description;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
