package com.ohmynone.rest.mapper;

import com.ohmynone.rest.dto.TagDTO;
import com.ohmynone.rest.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    Tag map(TagDTO dto);

    TagDTO map(Tag entity);

    @Mapping(target = "id", ignore = true)
    Tag map(TagDTO dto, @MappingTarget Tag tag);

    Set<TagDTO> map(Set<Tag> list);

}
