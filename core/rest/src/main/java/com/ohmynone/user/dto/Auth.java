package com.ohmynone.user.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class Auth {

    @NotBlank(message = "Username is mandatory")
    @Email
    public String username;

    @NotBlank(message = "Password is mandatory")
    @Length(min = 4)
    public String password;

}
