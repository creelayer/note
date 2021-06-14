package com.ohmynone.rest.service;

import com.ohmynone.rest.entity.Bookmark;
import com.ohmynone.rest.entity.BookmarkSearchData;
import com.ohmynone.rest.entity.Tag;
import com.ohmynone.rest.repository.BookmarkSearchDataRepository;
import com.ohmynone.rest.util.SearchUtil;
import org.springframework.stereotype.Service;

@Service
public class BookmarkSearchDataService {

    protected BookmarkSearchDataRepository searchDataRepository;

    public BookmarkSearchDataService(BookmarkSearchDataRepository searchDataRepository) {
        this.searchDataRepository = searchDataRepository;
    }

    public void delete(BookmarkSearchData bookmarkSearchData) {
        searchDataRepository.delete(bookmarkSearchData);
    }

    public BookmarkSearchData index(Bookmark bookmark) {

        BookmarkSearchData searchData = searchDataRepository
                .findByBookmarkId(bookmark.getId())
                .orElse(new BookmarkSearchData());

        StringBuilder sb = new StringBuilder(bookmark.getName());

        for (Tag tag : bookmark.getTags()) {
            sb.append(" ").append(tag.getName());
        }

        searchData.setBookmark(bookmark);
        searchData.setNormalizedText(SearchUtil.normalize(sb.toString()));

        return searchDataRepository.save(searchData);
    }
}
