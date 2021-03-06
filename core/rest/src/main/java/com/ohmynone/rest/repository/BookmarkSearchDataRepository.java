package com.ohmynone.rest.repository;

import com.ohmynone.rest.entity.BookmarkSearchData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BookmarkSearchDataRepository extends JpaRepository<BookmarkSearchData, Long> {
    Optional<BookmarkSearchData> findByBookmarkId(Long bookmarkId);
}
