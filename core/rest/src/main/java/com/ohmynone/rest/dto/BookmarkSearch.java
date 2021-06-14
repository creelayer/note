package com.ohmynone.rest.dto;

import com.ohmynone.rest.util.SearchUtil;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookmarkSearch {
    private Long bookId;
    private String s;

    public String getNormalizedText() {
        return SearchUtil.normalize(s);
    }

}
