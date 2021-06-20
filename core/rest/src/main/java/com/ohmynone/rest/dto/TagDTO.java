package com.ohmynone.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
public class TagDTO {

    public TagDTO(String name){
        this.name = name;
    }

    private Long id;

    @NotBlank(message = "Name is required")
    @Length(max = 255)
    private String name;

    @Length(max = 10)
    private String color;

}
