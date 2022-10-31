package com.book.reading.mapper;

import com.book.reading.dto.book.BookInfo;
import com.book.reading.model.book.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookInfo mapToBookDTO(Book book);

    Book mapToBook(BookInfo bookInfo);

    List<BookInfo> mapToBookDTOList(List<Book> bookList);

}
