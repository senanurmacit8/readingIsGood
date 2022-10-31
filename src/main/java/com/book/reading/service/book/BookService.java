package com.book.reading.service.book;

import com.book.reading.dto.book.BookInfo;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

     List<BookInfo> getAllBooks();
     ResponseEntity addNewBook( BookInfo bookInfo) throws Exception ;
     ResponseEntity updateBookStock(String bookId, String author,Integer stockNumber) throws OptimisticLockingFailureException;
     BookInfo getBookById(String bookId);
     }
