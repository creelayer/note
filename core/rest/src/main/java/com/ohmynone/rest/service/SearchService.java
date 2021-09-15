package com.ohmynone.rest.service;

import com.ohmynone.rest.dto.BookmarkSearchDto;
import com.ohmynone.rest.entity.Bookmark;
import com.ohmynone.rest.repository.BookmarkRepository;
import com.ohmynone.rest.repository.TagRepository;
import com.ohmynone.rest.repository.specification.BookmarkSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    protected BookmarkRepository bookmarkRepository;
    protected TagRepository tagRepository;

    public SearchService(BookmarkRepository bookmarkRepository, TagRepository tagRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.tagRepository = tagRepository;
    }

    public Page<Bookmark> search(BookmarkSearchDto filter, Pageable pageable) {
        return bookmarkRepository.findAll(BookmarkSpecification.filter(filter), pageable);
    }
}
