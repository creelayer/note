package com.ohmynone.rest.mapper;

import com.ohmynone.rest.dto.BookDto;
import com.ohmynone.rest.entity.Book;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-18T10:40:48+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book map(BookDto dto) {
        if ( dto == null ) {
            return null;
        }

        Book book = new Book();

        if ( dto.getId() != null ) {
            book.setId( dto.getId() );
        }
        book.setName( dto.getName() );

        return book;
    }

    @Override
    public Book map(BookDto dto, Book origin) {
        if ( dto == null ) {
            return null;
        }

        origin.setName( dto.getName() );

        return origin;
    }
}
