package com.ohmynone.rest.service;

import com.ohmynone.rest.dto.BookmarkSearch;
import com.ohmynone.rest.entity.Bookmark;
import com.ohmynone.rest.entity.BookmarkSearchData;
import com.ohmynone.rest.entity.Tag;
import com.ohmynone.rest.repository.BookmarkRepository;
import com.ohmynone.rest.repository.BookmarkSearchDataRepository;
import com.ohmynone.rest.util.SearchUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    protected BookmarkSearchDataRepository searchDateRepository;
    protected BookmarkRepository bookmarkRepository;

    public SearchService(BookmarkSearchDataRepository searchDateRepository, BookmarkRepository bookmarkRepository) {
        this.searchDateRepository = searchDateRepository;
        this.bookmarkRepository = bookmarkRepository;
    }

    public BookmarkSearchData index(Bookmark bookmark) {

        BookmarkSearchData searchData = searchDateRepository
                .findByBookmarkId(bookmark.getId())
                .orElse(new BookmarkSearchData());

        StringBuilder sb = new StringBuilder(bookmark.getName());

        for (Tag tag : bookmark.getTags()) {
            sb.append(" ").append(tag.getName());
        }

        searchData.setBookmark(bookmark);
        searchData.setNormalizedText(SearchUtil.normalize(sb.toString()));

        return searchDateRepository.save(searchData);
    }

    public Page<Bookmark> search(BookmarkSearch filter, Pageable pageable) {

        if (filter.getBookId() != null && filter.getS() != null) {
            return bookmarkRepository.search(filter.getBookId(), filter.getS(), pageable);
        } else if (filter.getBookId() == null && filter.getS() != null) {
            return bookmarkRepository.search(filter.getS(), pageable);
        } else if (filter.getBookId() != null) {
            return bookmarkRepository.findAllByBookIdOrderByIdAsc(filter.getBookId(), pageable);
        }

        return bookmarkRepository.findAllByOrderByIdAsc(pageable);
    }
}
