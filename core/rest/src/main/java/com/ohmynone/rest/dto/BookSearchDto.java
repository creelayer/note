package com.ohmynone.rest.dto;

import com.ohmynone.rest.entity.Identity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookSearchDto {
    private Long bookId;
    private Identity identity;
}
