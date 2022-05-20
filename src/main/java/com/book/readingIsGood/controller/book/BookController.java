package com.book.readingIsGood.controller.book;

import com.book.readingIsGood.dto.book.BookDTO;
import com.book.readingIsGood.service.book.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private BookService service;

    public BookController(BookService service){
        this.service= service;
    }

    @GetMapping("/allBooks")
    public List<BookDTO> getAllBooks(){
       List<BookDTO> bookDTOList=  service.getAllBooks();
        return bookDTOList;
    }

    @PostMapping("/addNewBook")
    public ResponseEntity addNewBook(@RequestParam BookDTO bookDTO) throws Exception {
      service.addNewBook(bookDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/updateBookStock")
    public ResponseEntity updateBookStock(@RequestParam String bookId, @RequestParam Integer stockNumber) throws Exception {
       service.updateBookStock(bookId,stockNumber);

        return new ResponseEntity(HttpStatus.OK);
    }

}
