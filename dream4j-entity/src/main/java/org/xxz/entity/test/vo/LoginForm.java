package org.xxz.entity.test.vo;

import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;

@Data
public class LoginForm implements java.io.Serializable {

    private static final long serialVersionUID = -5406467061334245346L;
    
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private Boolean rememberMe;
    @NotEmpty
    private String captcha;
    private String ip;

}
