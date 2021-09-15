package com.ohmynone.rest.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class BookmarkDto {

    private Long id;

    @NotNull
    private Long bookId;

    @NotBlank
    @Length(max = 255)
    private String name;

    private String body;

    private boolean pined;

    private Set<TagDto> tags = new HashSet<>();

    private BookDto book;
}
