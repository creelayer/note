package com.ohmynone.user.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class Register {

    @NotBlank(message = "Username is mandatory")
    @Email
    @Length(max = 100)
    public String username;

    @NotBlank(message = "Password is mandatory")
    @Length(min = 4, max = 100)
    public String password;

}
