package org.xxz.entity.test.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class RegisterForm {
    
    @NotEmpty(message = "{NotEmpty.registerForm.username}")
    @Length(min = 6, max = 12, message = "{Length.registerForm.username}")
    @Pattern(regexp = "[a-zA-Z0-9]{6, 12}", message = "{Pattern.registerForm.username}")
    private String username;
    
    @NotEmpty(message = "{NotEmpty.registerForm.password}")
    @Length(min = 6, max = 16, message = "{Length.registerForm.password}")
    private String password;
    
    private String repassword;
    
    @NotEmpty(message = "{NotEmpty.registerForm.captcha}")
    private String captcha;

}
