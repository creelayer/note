package com.ohmynone.rest.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class BookDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    @Length(max = 255)
    private String name;

}
