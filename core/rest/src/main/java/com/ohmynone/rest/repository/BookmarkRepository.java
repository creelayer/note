package com.ohmynone.rest.repository;

import com.ohmynone.rest.entity.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long>, PagingAndSortingRepository<Bookmark, Long> {

    Page<Bookmark> findAllByOrderByIdAsc(Pageable pageable);

    Page<Bookmark> findAllByBookIdOrderByIdAsc(Long bookId,  Pageable pageable);

    @Query(value = "select b.* from bookmark b " +
            "INNER JOIN bookmark_search_data bsd ON b.id = bsd.bookmark_id  " +
            "where normalized_text %>> ?1 " +
            "order by similarity(normalized_text, ?1) desc LIMIT ?#{ #pageable.pageSize } OFFSET ?#{ #pageable.offset }", nativeQuery = true)
    Page<Bookmark> search(String search, Pageable pageable);

    @Query(value = "select b.* from bookmark b " +
            "INNER JOIN bookmark_search_data bsd ON b.id = bsd.bookmark_id  " +
            "where b.book_id = ?1 normalized_text %>> ?2 " +
            "order by similarity(normalized_text, ?2) desc LIMIT ?#{ #pageable.pageSize } OFFSET ?#{ #pageable.offset }", nativeQuery = true)
    Page<Bookmark> search(Long bookId, String search, Pageable pageable);


}
