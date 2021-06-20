package com.ohmynone.rest.controller;

import com.ohmynone.rest.dto.BookmarkSearch;
import com.ohmynone.rest.dto.Response;
import com.ohmynone.rest.dto.SearchResult;
import com.ohmynone.rest.mapper.SearchMapper;
import com.ohmynone.rest.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/search")
public class SearchController {

    private final SearchService searchService;
    private final SearchMapper mapper;

    public SearchController(SearchService searchService, SearchMapper mapper) {
        this.searchService = searchService;
        this.mapper = mapper;
    }

    @GetMapping("live")
    Response<Page<SearchResult>> index(@Valid BookmarkSearch filter,
                                       Pageable pageable,
                                       Response<Page<SearchResult>> model) {
        return model.setData(searchService.search(filter, pageable).map(mapper::map));
    }
}
