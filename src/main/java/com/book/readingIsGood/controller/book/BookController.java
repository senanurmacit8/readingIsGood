package com.book.readingIsGood.controller.book;

import com.book.readingIsGood.dto.book.BookDTO;
import com.book.readingIsGood.service.book.BookService;
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
    public String addNewBook(@RequestParam BookDTO bookDTO) throws Exception {
      service.addNewBook(bookDTO);

        return "success";
    }

    @PostMapping("/updateBookStock")
    public String updateBookStock(@RequestParam String bookId, @RequestParam Integer stockNumber) throws Exception {
       service.updateBookStock(bookId,stockNumber);

        return "success";
    }


}
