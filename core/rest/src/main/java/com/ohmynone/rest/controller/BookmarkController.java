package com.ohmynone.rest.controller;

import com.ohmynone.rest.dto.BookmarkDto;
import com.ohmynone.rest.dto.Response;
import com.ohmynone.rest.entity.Book;
import com.ohmynone.rest.entity.Bookmark;
import com.ohmynone.rest.mapper.BookmarkMapper;
import com.ohmynone.rest.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<?> view(@PathVariable("id") Bookmark bookmark) {
        return ResponseEntity.ok(new Response<>(bookmark));
    }

    @PostMapping("add/{id}")
    @PreAuthorize("#book.identity.username == principal.username")
    ResponseEntity<?> createBookmark(@PathVariable("id") Book book,
                                         @Valid @RequestBody BookmarkDto dto) {
        Bookmark bookmark = bookmarkService.addBookmark(book, dto);
        return ResponseEntity.ok(new Response<>(bookmark));
    }

    @PostMapping("{id}")
    @PreAuthorize("#bookmark.book.identity.username == principal.username")
    ResponseEntity<?> updateBookmark(@PathVariable("id") Bookmark bookmark,
                                         @Valid @RequestBody BookmarkDto dto) {
        bookmark = bookmarkService.updateBookmark(bookmark, dto);
        return ResponseEntity.ok(new Response<>(bookmark));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("#bookmark.book.identity.username == principal.username")
    ResponseEntity<?> deleteBookmark(@PathVariable("id") Bookmark bookmark) {
        bookmarkService.delete(bookmark);
        return ResponseEntity.ok(new Response<>(bookmark));
    }

    @PostMapping("{id}/pin")
    @PreAuthorize("#bookmark.book.identity.username == principal.username")
    ResponseEntity<?> pinBookmark(@PathVariable("id") Bookmark bookmark) {
        bookmark.setPined(!bookmark.isPined());
        return ResponseEntity.ok(new Response<>(bookmark));
    }
}
