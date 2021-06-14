package com.ohmynone.rest.service;

import com.ohmynone.rest.dto.BookmarkSearch;
import com.ohmynone.rest.entity.Bookmark;
import com.ohmynone.rest.repository.BookmarkRepository;
import com.ohmynone.rest.repository.specification.BookmarkSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    protected BookmarkRepository bookmarkRepository;

    public SearchService( BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public Page<Bookmark> search(BookmarkSearch filter, Pageable pageable) {
        return bookmarkRepository.findAll(BookmarkSpecification.filter(filter), pageable);
    }

}
