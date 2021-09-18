package com.ohmynone.rest.mapper;

import com.ohmynone.rest.dto.BookDto;
import com.ohmynone.rest.dto.BookmarkDto;
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
    date = "2021-09-18T10:40:49+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15 (Oracle Corporation)"
)
@Component
public class BookmarkMapperImpl implements BookmarkMapper {

    @Override
    public Bookmark map(BookmarkDto dto) {
        if ( dto == null ) {
            return null;
        }

        Bookmark bookmark = new Bookmark();

        if ( dto.getId() != null ) {
            bookmark.setId( dto.getId() );
        }
        bookmark.setBook( bookDtoToBook( dto.getBook() ) );
        bookmark.setName( dto.getName() );
        bookmark.setBody( dto.getBody() );
        bookmark.setPined( dto.isPined() );

        return bookmark;
    }

    @Override
    public Bookmark map(BookmarkDto dto, Bookmark origin) {
        if ( dto == null ) {
            return null;
        }

        origin.setName( dto.getName() );
        origin.setBody( dto.getBody() );
        origin.setPined( dto.isPined() );

        return origin;
    }

    @Override
    public BookmarkDto map(Bookmark bookmark) {
        if ( bookmark == null ) {
            return null;
        }

        BookmarkDto bookmarkDto = new BookmarkDto();

        bookmarkDto.setBookId( bookmarkBookId( bookmark ) );
        bookmarkDto.setId( bookmark.getId() );
        bookmarkDto.setName( bookmark.getName() );
        bookmarkDto.setBody( bookmark.getBody() );
        bookmarkDto.setPined( bookmark.isPined() );
        bookmarkDto.setTags( tagSetToTagDtoSet( bookmark.getTags() ) );
        bookmarkDto.setBook( bookToBookDto( bookmark.getBook() ) );

        return bookmarkDto;
    }

    @Override
    public List<BookmarkDto> map(List<Bookmark> list) {
        if ( list == null ) {
            return null;
        }

        List<BookmarkDto> list1 = new ArrayList<BookmarkDto>( list.size() );
        for ( Bookmark bookmark : list ) {
            list1.add( map( bookmark ) );
        }

        return list1;
    }

    protected Book bookDtoToBook(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        if ( bookDto.getId() != null ) {
            book.setId( bookDto.getId() );
        }
        book.setName( bookDto.getName() );

        return book;
    }

    private Long bookmarkBookId(Bookmark bookmark) {
        if ( bookmark == null ) {
            return null;
        }
        Book book = bookmark.getBook();
        if ( book == null ) {
            return null;
        }
        long id = book.getId();
        return id;
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

    protected BookDto bookToBookDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setId( book.getId() );
        bookDto.setName( book.getName() );

        return bookDto;
    }
}
