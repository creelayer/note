package com.ohmynone.rest.controller;

import com.ohmynone.rest.dto.BookmarkSearchDto;
import com.ohmynone.rest.dto.Response;
import com.ohmynone.rest.entity.Identity;
import com.ohmynone.rest.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("live")
    ResponseEntity<?> index(@Valid BookmarkSearchDto filter,
                            Pageable pageable,
                            @AuthenticationPrincipal Identity identity) {
        filter.setIdentity(identity);
        return ResponseEntity.ok(new Response<>(searchService.search(filter, pageable)));
    }
}
