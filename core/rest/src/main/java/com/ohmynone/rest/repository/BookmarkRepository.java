package com.ohmynone.rest.repository;

import com.ohmynone.rest.entity.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long>, PagingAndSortingRepository<Bookmark, Long>, JpaSpecificationExecutor<Bookmark> {
    Page<Bookmark> findAllByOrderByIdAsc(Pageable pageable);
    Page<Bookmark> findAllByBookIdOrderByIdAsc(Long bookId,  Pageable pageable);

    Page<Bookmark> findAllByDeletedAtIsNullOrderByIdAsc(Pageable pageable);
    Page<Bookmark> findAllByBookIdAndDeletedAtIsNullOrderByIdAsc(Long bookId,  Pageable pageable);
}
