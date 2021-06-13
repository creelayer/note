package com.ohmynone.rest.service;

import com.ohmynone.rest.dto.BookmarkDTO;
import com.ohmynone.rest.entity.Book;
import com.ohmynone.rest.mapper.BookmarkMapper;
import com.ohmynone.rest.repository.BookmarkRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookmarkService {

    protected BookmarkRepository bookmarkRepository;
    protected SearchService searchService;
    protected TagService tagService;
    protected BookmarkMapper mapper;

    public BookmarkService(TagService tagService, SearchService searchService, BookmarkRepository bookmarkRepository, BookmarkMapper mapper) {
        this.bookmarkRepository = bookmarkRepository;
        this.searchService = searchService;
        this.tagService = tagService;
        this.mapper = mapper;
    }

    public Optional<com.ohmynone.rest.entity.Bookmark> findOne(Long id) {
        return bookmarkRepository.findById(id);
    }

    public com.ohmynone.rest.entity.Bookmark save(com.ohmynone.rest.entity.Bookmark book) {
        return bookmarkRepository.save(book);
    }

    public void delete(com.ohmynone.rest.entity.Bookmark book) {
        bookmarkRepository.delete(book);
    }

    public com.ohmynone.rest.entity.Bookmark addBookmarkByDto(Book book, BookmarkDTO dto) {
        com.ohmynone.rest.entity.Bookmark bookmark = mapper.map(dto);
        bookmark.setBook(book);
        bookmark.setTags(tagService.addUserTags(book.getUser(), dto.getTags()));
        save(bookmark);
        searchService.index(bookmark);
        return bookmark;
    }

    public com.ohmynone.rest.entity.Bookmark updateBookmarkByDto(com.ohmynone.rest.entity.Bookmark bookmark, BookmarkDTO dto) {
        bookmark = mapper.map(dto, bookmark);
        bookmark.setTags(tagService.addUserTags(bookmark.getBook().getUser(), dto.getTags()));
        save(bookmark);
        searchService.index(bookmark);
        return bookmark;
    }

}
