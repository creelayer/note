package com.ohmynone.rest.mapper;

import com.ohmynone.rest.dto.BookDTO;
import com.ohmynone.rest.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    Book map(BookDTO dto);

    @Mapping(target = "id", ignore = true)
    Book map(BookDTO dto, @MappingTarget Book origin);

    BookDTO map(Book entity);

    List<BookDTO> map(List<Book> list);
}
