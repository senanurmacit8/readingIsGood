package com.book.readingIsGood.service.book;

import com.book.readingIsGood.dto.book.BookDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

     List<BookDTO> getAllBooks();
     ResponseEntity addNewBook( BookDTO bookDTO) throws Exception ;
     ResponseEntity updateBookStock(String bookId, Integer stockNumber) throws Exception ;
     BookDTO getBookById(String bookId);

     }
