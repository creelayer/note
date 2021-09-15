package com.ohmynone.rest.service;

import com.ohmynone.rest.dto.BookmarkDto;
import com.ohmynone.rest.dto.TagDto;
import com.ohmynone.rest.entity.Book;
import com.ohmynone.rest.entity.Bookmark;
import com.ohmynone.rest.mapper.BookmarkMapper;
import com.ohmynone.rest.repository.BookmarkRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookmarkService {

    protected BookmarkRepository bookmarkRepository;
    protected BookmarkSearchDataService bookmarkSearchDataService;
    protected TagService tagService;
    protected BookmarkMapper mapper;

    public BookmarkService(TagService tagService,
                           BookmarkSearchDataService bookmarkSearchDataService,
                           BookmarkRepository bookmarkRepository,
                           BookmarkMapper mapper) {
        this.bookmarkRepository = bookmarkRepository;
        this.bookmarkSearchDataService = bookmarkSearchDataService;
        this.tagService = tagService;
        this.mapper = mapper;
    }

    public Optional<Bookmark> findOne(Long id) {
        return bookmarkRepository.findById(id);
    }

    public Page<Bookmark> list(Pageable pageable) {
        return bookmarkRepository.findAllByDeletedAtIsNullOrderByIdAsc(pageable);
    }

    public Page<Bookmark> list(Book book, Pageable pageable) {
        return bookmarkRepository.findAllByBookIdAndDeletedAtIsNullOrderByIdAsc(book.getId(), pageable);
    }

    public Bookmark save(Bookmark book) {
        return bookmarkRepository.save(book);
    }

    public void delete(Bookmark bookmark) {
        bookmark.setDeletedAt(LocalDateTime.now());
        save(bookmark);
    }

    public Bookmark addBookmark(Book book, BookmarkDto dto) {
        Bookmark bookmark = mapper.map(dto);
        bookmark.setBook(book);
        if (dto.getTags() != null) {
            Set<String> names = dto.getTags().stream().map(TagDto::getName).collect(Collectors.toSet());
            bookmark.setTags(tagService.addTags(book.getIdentity(), names));
        }
        save(bookmark);
        bookmarkSearchDataService.index(bookmark);
        return bookmark;
    }

    public Bookmark updateBookmark(Bookmark bookmark, BookmarkDto dto) {
        bookmark = mapper.map(dto, bookmark);
        Set<String> names = dto.getTags().stream().map(TagDto::getName).collect(Collectors.toSet());
        bookmark.setTags(tagService.addTags(bookmark.getBook().getIdentity(), names));
        save(bookmark);
        bookmarkSearchDataService.index(bookmark);
        return bookmark;
    }

}
