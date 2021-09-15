package com.ohmynone.rest.mapper;

import com.ohmynone.rest.dto.TagDto;
import com.ohmynone.rest.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    Tag map(TagDto dto);

    TagDto map(Tag entity);

    @Mapping(target = "id", ignore = true)
    Tag map(TagDto dto, @MappingTarget Tag tag);

    List<TagDto> map(List<Tag> list);

}
