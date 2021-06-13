package com.ohmynone.rest.mapper;

import com.ohmynone.rest.dto.TagDTO;
import com.ohmynone.rest.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    Tag map(TagDTO dto);

    TagDTO map(Tag entity);

    Set<TagDTO> map(Set<Tag> list);

}
