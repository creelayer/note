package com.ohmynone.rest.controller;

import com.ohmynone.rest.component.CurrentUserDetails;
import com.ohmynone.rest.dto.BookmarkDTO;
import com.ohmynone.rest.dto.BookmarkSearch;
import com.ohmynone.rest.dto.Response;
import com.ohmynone.rest.entity.Book;
import com.ohmynone.rest.entity.Bookmark;
import com.ohmynone.rest.mapper.BookmarkMapper;
import com.ohmynone.rest.service.BookService;
import com.ohmynone.rest.service.BookmarkService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final BookService bookService;
    private final BookmarkMapper mapper;

    public BookmarkController(BookmarkService bookmarkService, BookService bookService, BookmarkMapper mapper) {
        this.bookmarkService = bookmarkService;
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @GetMapping("list")
    Response<Page<BookmarkDTO>> index(Pageable pageable, Response<Page<BookmarkDTO>> model) {
        return model.setData(bookmarkService.list(pageable).map(mapper::map));
    }

    @GetMapping("list/{id}")
    Response<Page<BookmarkDTO>> index(@PathVariable Long id, Pageable pageable, Response<Page<BookmarkDTO>> model) {
        Book book = bookService.findOne(id).orElseThrow();
        return model.setData(bookmarkService.list(book, pageable).map(mapper::map));
    }

    @GetMapping("{id}")
    Response<BookmarkDTO> index(@PathVariable Long id, Response<BookmarkDTO> model) {
        Bookmark bookmark = bookmarkService.findOne(id).orElseThrow();
        var a = mapper.map(bookmark);
        return model.setData(mapper.map(bookmark));
    }

    @PostMapping("")
    Response<BookmarkDTO> createBookmark(@Valid @RequestBody BookmarkDTO dto,
                                         @AuthenticationPrincipal CurrentUserDetails userDetails,
                                         Response<BookmarkDTO> model) {
        Book book = bookService.findOne(dto.getBookId()).orElseThrow();

        Bookmark bookmark = bookmarkService.addBookmark(book, dto);
        return model.setData(mapper.map(bookmark));
    }

    @PostMapping("/{id}")
    Response<BookmarkDTO> updateBookmark(@PathVariable Long id,
                                         @Valid @RequestBody BookmarkDTO dto,
                                         @AuthenticationPrincipal CurrentUserDetails userDetails,
                                         Response<BookmarkDTO> model) {
        Bookmark bookmark = bookmarkService.findOne(id).orElseThrow();
        bookmark = bookmarkService.updateBookmark(bookmark, dto);
        return model.setData(mapper.map(bookmark));
    }

    @DeleteMapping("/{id}")
    Response<BookmarkDTO> deleteBookmark(@PathVariable Long id,
                                         @AuthenticationPrincipal CurrentUserDetails userDetails,
                                         Response<BookmarkDTO> model) {
        Bookmark bookmark = bookmarkService.findOne(id).orElseThrow();
        bookmarkService.delete(bookmark);
        return model.setData(mapper.map(bookmark));
    }

}
