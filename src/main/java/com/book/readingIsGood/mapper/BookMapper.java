package com.book.readingIsGood.mapper;

import com.book.readingIsGood.dto.book.BookDTO;
import com.book.readingIsGood.model.book.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO mapToBookDTO(Book book);

    Book mapToBook(BookDTO bookDTO);

    List<BookDTO> mapToBookDTOList(List<Book> bookList);

}
