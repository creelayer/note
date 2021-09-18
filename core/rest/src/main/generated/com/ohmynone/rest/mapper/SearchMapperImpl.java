package com.ohmynone.rest.mapper;

import com.ohmynone.rest.dto.BookDto;
import com.ohmynone.rest.dto.SearchResult;
import com.ohmynone.rest.dto.TagDto;
import com.ohmynone.rest.entity.Book;
import com.ohmynone.rest.entity.Bookmark;
import com.ohmynone.rest.entity.Tag;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-18T10:40:48+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class SearchMapperImpl implements SearchMapper {

    @Override
    public SearchResult map(Bookmark bookmark) {
        if ( bookmark == null ) {
            return null;
        }

        SearchResult searchResult = new SearchResult();

        searchResult.setId( bookmark.getId() );
        searchResult.setBook( bookToBookDto( bookmark.getBook() ) );
        searchResult.setName( bookmark.getName() );
        searchResult.setPined( bookmark.isPined() );
        searchResult.setTags( tagSetToTagDtoSet( bookmark.getTags() ) );

        return searchResult;
    }

    @Override
    public List<SearchResult> map(List<Bookmark> list) {
        if ( list == null ) {
            return null;
        }

        List<SearchResult> list1 = new ArrayList<SearchResult>( list.size() );
        for ( Bookmark bookmark : list ) {
            list1.add( map( bookmark ) );
        }

        return list1;
    }

    protected BookDto bookToBookDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setId( book.getId() );
        bookDto.setName( book.getName() );

        return bookDto;
    }

    protected TagDto tagToTagDto(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDto tagDto = new TagDto();

        tagDto.setId( tag.getId() );
        tagDto.setName( tag.getName() );
        tagDto.setColor( tag.getColor() );

        return tagDto;
    }

    protected Set<TagDto> tagSetToTagDtoSet(Set<Tag> set) {
        if ( set == null ) {
            return null;
        }

        Set<TagDto> set1 = new HashSet<TagDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Tag tag : set ) {
            set1.add( tagToTagDto( tag ) );
        }

        return set1;
    }
}
