package com.ohmynone.rest.mapper;

import com.ohmynone.rest.dto.BookDto;
import com.ohmynone.rest.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    Book map(BookDto dto);

    @Mapping(target = "id", ignore = true)
    Book map(BookDto dto, @MappingTarget Book origin);

}
