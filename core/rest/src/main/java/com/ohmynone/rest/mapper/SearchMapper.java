package com.ohmynone.rest.mapper;

import com.ohmynone.rest.dto.SearchResult;
import com.ohmynone.rest.entity.Bookmark;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SearchMapper {

    SearchResult map(Bookmark bookmark);

    List<SearchResult> map(List<Bookmark> list);

   // TagDto map(Tag entity);
}
