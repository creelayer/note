package com.ohmynone.rest.service;

import com.ohmynone.rest.dto.BookSearchDto;
import com.ohmynone.rest.entity.Book;
import com.ohmynone.rest.entity.Identity;
import com.ohmynone.rest.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookService {

    protected BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> findOne(Long id) {
        return bookRepository.findById(id);
    }

    public Page<Book> search(BookSearchDto filter, Pageable pageable) {
        if (filter.getIdentity() == null) {
            return bookRepository.findAllByDeletedAtIsNullOrderByIdAsc(pageable);
        }
        return bookRepository.findAllByIdentityAndDeletedAtIsNullOrderByIdAsc(filter.getIdentity(), pageable);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book delete(Book book) {
        book.setDeletedAt(LocalDateTime.now());
        return save(book);
    }

}
