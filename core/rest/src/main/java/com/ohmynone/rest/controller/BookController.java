package com.ohmynone.rest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ohmynone.rest.dto.BookDto;
import com.ohmynone.rest.dto.BookSearchDto;
import com.ohmynone.rest.dto.BookmarkSearchDto;
import com.ohmynone.rest.dto.Response;
import com.ohmynone.rest.entity.Book;
import com.ohmynone.rest.entity.Identity;
import com.ohmynone.rest.mapper.BookMapper;
import com.ohmynone.rest.service.BookService;
import com.ohmynone.rest.view.BookView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/book")
public class BookController {

    private final BookService bookService;
    private final BookMapper mapper;

    public BookController(BookService bookService, BookMapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @GetMapping("")
    @JsonView({BookView.List.class})
    Response<Page<Book>> index(@Valid BookSearchDto filter,
                                  Pageable pageable,
                                  @AuthenticationPrincipal Identity identity,
                                  Response<Page<Book>> model
    ) {
        filter.setIdentity(identity);
        return model.setData(bookService.search(filter, pageable));
    }

    @PostMapping("")
    Response<Book> createBook(@Valid @RequestBody BookDto dto,
                                 @AuthenticationPrincipal Identity identity,
                                 Response<Book> model
    ) {
        Book book = mapper.map(dto);
        book.setIdentity(identity);
        return model.setData(bookService.save(book));
    }

    @PostMapping("/{id}")
    @PreAuthorize("#book.identity.username == principal.username")
    Response<Book> updateBook(@PathVariable("id") Book book,
                                 @Valid @RequestBody BookDto dto,
                                 Response<Book> model
    ) {
        book = mapper.map(dto, book);
        return model.setData(bookService.save(book));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#book.identity.username == principal.username")
    Response<Book> deleteBook(@PathVariable("id") Book book,
                                 Response<Book> model
    ) {
        return model.setData(bookService.delete(book));
    }
}
