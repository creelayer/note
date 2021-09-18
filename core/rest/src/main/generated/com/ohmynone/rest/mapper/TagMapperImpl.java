package com.ohmynone.rest.mapper;

import com.ohmynone.rest.dto.TagDto;
import com.ohmynone.rest.entity.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-18T10:40:48+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public Tag map(TagDto dto) {
        if ( dto == null ) {
            return null;
        }

        Tag tag = new Tag();

        if ( dto.getId() != null ) {
            tag.setId( dto.getId() );
        }
        tag.setName( dto.getName() );
        tag.setColor( dto.getColor() );

        return tag;
    }

    @Override
    public TagDto map(Tag entity) {
        if ( entity == null ) {
            return null;
        }

        TagDto tagDto = new TagDto();

        tagDto.setId( entity.getId() );
        tagDto.setName( entity.getName() );
        tagDto.setColor( entity.getColor() );

        return tagDto;
    }

    @Override
    public Tag map(TagDto dto, Tag tag) {
        if ( dto == null ) {
            return null;
        }

        tag.setName( dto.getName() );
        tag.setColor( dto.getColor() );

        return tag;
    }

    @Override
    public List<TagDto> map(List<Tag> list) {
        if ( list == null ) {
            return null;
        }

        List<TagDto> list1 = new ArrayList<TagDto>( list.size() );
        for ( Tag tag : list ) {
            list1.add( map( tag ) );
        }

        return list1;
    }
}
