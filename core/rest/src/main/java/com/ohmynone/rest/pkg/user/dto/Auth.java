package com.ohmynone.rest.pkg.user.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class Auth {

    @NotBlank(message = "Login is mandatory")
    @Email
    public String login;

    @NotBlank(message = "Password is mandatory")
    @Length(min = 4)
    public String password;

}
