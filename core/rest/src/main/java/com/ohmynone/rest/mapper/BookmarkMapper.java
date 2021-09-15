package com.ohmynone.rest.mapper;

import com.ohmynone.rest.dto.BookmarkDto;
import com.ohmynone.rest.entity.Bookmark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookmarkMapper {

    @Mapping(target = "tags", ignore = true)
    Bookmark map(BookmarkDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "book", ignore = true)
    Bookmark map(BookmarkDto dto, @MappingTarget Bookmark origin);

    @Mapping(target = "bookId", source = "book.id")
    BookmarkDto map(Bookmark bookmark);

    List<BookmarkDto> map(List<Bookmark> list);

//    default String map(Tag tag){
//        return tag.getName();
//    }
}
