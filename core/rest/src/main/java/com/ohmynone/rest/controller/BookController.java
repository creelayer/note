package com.ohmynone.rest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ohmynone.rest.dto.BookDto;
import com.ohmynone.rest.dto.BookSearchDto;
import com.ohmynone.rest.dto.Response;
import com.ohmynone.rest.entity.Book;
import com.ohmynone.rest.entity.Identity;
import com.ohmynone.rest.mapper.BookMapper;
import com.ohmynone.rest.service.BookService;
import com.ohmynone.rest.view.BookView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper mapper;

    @GetMapping("")
    @JsonView({BookView.List.class})
    ResponseEntity<?> index(@Valid BookSearchDto filter,
                            Pageable pageable,
                            @AuthenticationPrincipal Identity identity
    ) {
        filter.setIdentity(identity);
        return ResponseEntity.ok(new Response<>(bookService.search(filter, pageable)));
    }

    @PostMapping("")
    ResponseEntity<?> createBook(@Valid @RequestBody BookDto dto, @AuthenticationPrincipal Identity identity) {
        Book book = mapper.map(dto);
        book.setIdentity(identity);
        return ResponseEntity.ok(new Response<>(bookService.save(book)));
    }

    @PostMapping("/{id}")
    @PreAuthorize("#book.identity.username == principal.username")
    ResponseEntity<?> updateBook(@PathVariable("id") Book book, @Valid @RequestBody BookDto dto) {
        book = mapper.map(dto, book);
        return ResponseEntity.ok(new Response<>(bookService.save(book)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#book.identity.username == principal.username")
    ResponseEntity<?> deleteBook(@PathVariable("id") Book book) {
        return ResponseEntity.ok(new Response<>(bookService.delete(book)));
    }
}
