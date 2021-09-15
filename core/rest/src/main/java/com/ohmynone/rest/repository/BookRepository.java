package com.ohmynone.rest.repository;

import com.ohmynone.rest.entity.Book;
import com.ohmynone.rest.entity.Identity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, PagingAndSortingRepository<Book, Long> {
    Page<Book> findAllByDeletedAtIsNullOrderByIdAsc(Pageable pageable);
    Page<Book> findAllByIdentityAndDeletedAtIsNullOrderByIdAsc(Identity identity, Pageable pageable);
}
