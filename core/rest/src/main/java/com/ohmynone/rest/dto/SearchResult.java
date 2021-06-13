package com.ohmynone.rest.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Setter
@Getter
public class SearchResult {

    private Long id;

    private BookDTO book;

    @NotBlank(message = "Name is required")
    @Length(max = 255)
    private String name;

    private Set<String> tags;

}
