package com.ohmynone.auth.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UserDto {

    @NotBlank(message = "Email is required")
    @Email
    @Length(max = 255)
    private String email;

    @NotBlank(message = "Password is required")
    @Length(max = 30)
    private String password;

}
