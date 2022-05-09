package com.book.readingIsGood.service.book;

import com.book.readingIsGood.dto.book.BookDTO;

import java.util.List;

public interface BookService {

     List<BookDTO> getAllBooks();
     String addNewBook( BookDTO bookDTO) throws Exception ;
     String updateBookStock(String bookId, Integer stockNumber) throws Exception ;
     public BookDTO getBookById(String bookId);


     }
