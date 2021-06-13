package com.ohmynone.rest.service;

import com.ohmynone.rest.entity.Book;
import com.ohmynone.rest.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<Book> search(Pageable pageable) {
        return bookRepository.findAllByOrderByIdAsc(pageable);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

}
