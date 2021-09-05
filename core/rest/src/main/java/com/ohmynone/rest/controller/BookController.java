package com.ohmynone.rest.controller;

import com.ohmynone.rest.dto.BookDTO;
import com.ohmynone.rest.dto.Response;
import com.ohmynone.rest.entity.Book;
import com.ohmynone.rest.entity.Identity;
import com.ohmynone.rest.mapper.BookMapper;
import com.ohmynone.rest.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Response<Page<BookDTO>> index(Pageable pageable, Response<Page<BookDTO>> model) {
            return model.setData(bookService.search(pageable).map(mapper::map));
    }

    @PostMapping("")
    Response<BookDTO> createBook(@Valid @RequestBody BookDTO dto,
                                 @AuthenticationPrincipal Identity identity,
                                 Response<BookDTO> model) {
        com.ohmynone.rest.entity.Book book = mapper.map(dto);
        book.setIdentity(identity);
        bookService.save(book);
        return model.setData(mapper.map(book));
    }

    @PostMapping("/{id}")
    Response<BookDTO> updateBook(@PathVariable Long id,
                                 @Valid @RequestBody BookDTO dto,
                                 @AuthenticationPrincipal Identity identity,
                                 Response<BookDTO> model) {
        Book book = mapper.map(dto, bookService.findOne(id).orElseThrow());
        return model.setData(mapper.map(bookService.save(book)));
    }

    @DeleteMapping("/{id}")
    Response<BookDTO> deleteBook(@PathVariable Long id,
                                 @AuthenticationPrincipal Identity identity,
                                 Response<BookDTO> model) {
        Book book = bookService.findOne(id).orElseThrow();
        bookService.delete(book);
        return model.setData(mapper.map(book));
    }
}
