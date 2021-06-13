package com.ohmynone.rest.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter
@Getter
public class BookmarkDTO {

    private Long id;

    @NotNull
    private Long bookId;

    @NotBlank
    @Length(max = 255)
    private String name;

    private String body;

    private Set<String> tags;
}
