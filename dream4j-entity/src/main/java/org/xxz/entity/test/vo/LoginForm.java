package org.xxz.entity.test.vo;

import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;

@Data
public class LoginForm implements java.io.Serializable {

    private static final long serialVersionUID = -5406467061334245346L;
    
    @NotEmpty(message="{NotEmpty.loginForm.username}")
    private String username;
    @NotEmpty(message="{NotEmpty.loginForm.password}")
    private String password;
    private Boolean rememberMe;
    @NotEmpty(message="{NotEmpty.loginForm.captcha}")
    private String captcha;
    private String ip;

}
