package com.book.readingIsGood.model.book;

import com.book.readingIsGood.dto.book.BookDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListBookResponse {
     List<BookDTO> bookDTOList;
}
