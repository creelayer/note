package com.ohmynone.rest.controller;

import com.ohmynone.rest.dto.BookmarkSearchDto;
import com.ohmynone.rest.dto.Response;
import com.ohmynone.rest.dto.SearchResult;
import com.ohmynone.rest.entity.Identity;
import com.ohmynone.rest.mapper.SearchMapper;
import com.ohmynone.rest.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;
    private final SearchMapper mapper;

    @GetMapping("live")
    Response<Page<SearchResult>> index(@Valid BookmarkSearchDto filter,
                                       Pageable pageable,
                                       @AuthenticationPrincipal Identity identity,
                                       Response<Page<SearchResult>> model) {
        filter.setIdentity(identity);
        return model.setData(searchService.search(filter, pageable).map(mapper::map));
    }
}
