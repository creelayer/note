package com.ohmynone.rest.dto;

import com.ohmynone.rest.entity.Identity;
import com.ohmynone.rest.util.SearchUtil;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookmarkSearchDto {
    private Long bookId;
    private String search;
    private Identity identity;

    public String getNormalizedText() {
        return SearchUtil.normalize(search);
    }

}
