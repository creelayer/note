package com.ohmynone.rest.mapper;

import com.ohmynone.rest.dto.BookmarkDTO;
import com.ohmynone.rest.entity.Bookmark;
import com.ohmynone.rest.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookmarkMapper {

    @Mapping(target = "tags", ignore = true)
    Bookmark map(BookmarkDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "book", ignore = true)
    Bookmark map(BookmarkDTO dto, @MappingTarget Bookmark origin);

    BookmarkDTO map(Bookmark bookmark);

    List<BookmarkDTO> map(List<Bookmark> list);

//    default String map(Tag tag){
//        return tag.getName();
//    }
}
