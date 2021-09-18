package com.ohmynone.rest.controller;

import com.ohmynone.rest.dto.BookmarkDto;
import com.ohmynone.rest.dto.Response;
import com.ohmynone.rest.entity.Book;
import com.ohmynone.rest.entity.Bookmark;
import com.ohmynone.rest.mapper.BookmarkMapper;
import com.ohmynone.rest.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("v1/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final BookmarkMapper mapper;

    @GetMapping("{id}")
    @PreAuthorize("#bookmark.book.identity.username == principal.username")
    Response<Bookmark> view(@PathVariable("id") Bookmark bookmark, Response<Bookmark> model) {
        return model.setData(bookmark);
    }

    @PostMapping("add/{id}")
    @PreAuthorize("#book.identity.username == principal.username")
    Response<Bookmark> createBookmark(@PathVariable("id") Book book,
                                         @Valid @RequestBody BookmarkDto dto,
                                         Response<Bookmark> model) {
        Bookmark bookmark = bookmarkService.addBookmark(book, dto);
        return model.setData(bookmark);
    }

    @PostMapping("{id}")
    @PreAuthorize("#bookmark.book.identity.username == principal.username")
    Response<Bookmark> updateBookmark(@PathVariable("id") Bookmark bookmark,
                                         @Valid @RequestBody BookmarkDto dto,
                                         Response<Bookmark> model) {
        bookmark = bookmarkService.updateBookmark(bookmark, dto);
        return model.setData(bookmark);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("#bookmark.book.identity.username == principal.username")
    Response<Bookmark> deleteBookmark(@PathVariable("id") Bookmark bookmark,
                                         Response<Bookmark> model) {
        bookmarkService.delete(bookmark);
        return model.setData(bookmark);
    }

    @PostMapping("{id}/pin")
    @PreAuthorize("#bookmark.book.identity.username == principal.username")
    Response<Bookmark> pinBookmark(@PathVariable("id") Bookmark bookmark,
                                         Response<Bookmark> model) {
        bookmark.setPined(!bookmark.isPined());
        return model.setData(bookmarkService.save(bookmark));
    }
}
