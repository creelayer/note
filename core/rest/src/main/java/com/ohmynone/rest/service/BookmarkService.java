package com.ohmynone.rest.service;

import com.ohmynone.rest.dto.BookmarkDTO;
import com.ohmynone.rest.entity.Book;
import com.ohmynone.rest.entity.Bookmark;
import com.ohmynone.rest.entity.BookmarkSearchData;
import com.ohmynone.rest.mapper.BookmarkMapper;
import com.ohmynone.rest.repository.BookmarkRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
        BookmarkSearchData searchData = bookmark.getSearchData();
        if (searchData != null)
            bookmarkSearchDataService.delete(searchData);

        bookmark.setDeletedAt(LocalDateTime.now());
        save(bookmark);
    }

    public Bookmark addBookmark(Book book, BookmarkDTO dto) {
        Bookmark bookmark = mapper.map(dto);
        bookmark.setBook(book);
        if (dto.getTags() != null) {
            bookmark.setTags(tagService.addUserTags(book.getUser(), dto.getTags()));
        }
        save(bookmark);
        bookmarkSearchDataService.index(bookmark);
        return bookmark;
    }

    public Bookmark updateBookmark(Bookmark bookmark, BookmarkDTO dto) {
        bookmark = mapper.map(dto, bookmark);
        bookmark.setTags(tagService.addUserTags(bookmark.getBook().getUser(), dto.getTags()));
        save(bookmark);
        bookmarkSearchDataService.index(bookmark);
        return bookmark;
    }

}
