package com.book.reading.controller.book;

import com.book.reading.dto.book.BookInfo;
import com.book.reading.service.book.BookService;
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

    public BookController(BookService service) {
        this.service = service;
    }

    /**
     * This method created for getting all Books
     *
     * @return
     */
    @GetMapping("/allBooks")
    public List<BookInfo> getAllBooks() {
        return service.getAllBooks();
    }

    /**
     * This method is created to add new Book
     *
     * @param bookInfo
     * @return
     * @throws Exception
     */
    @PostMapping("/addNewBook")
    public ResponseEntity<String> addNewBook(@RequestParam BookInfo bookInfo) throws Exception {
        service.addNewBook(bookInfo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * This method is created from updating Book Stock in DB.
     *
     * @param bookId
     * @param stockNumber
     * @return
     * @throws Exception
     */
    @PostMapping("/updateBookStock")
    public ResponseEntity<String> updateBookStock(@RequestParam String bookId, @RequestParam Integer stockNumber) throws Exception {
        service.updateBookStock(bookId,"",stockNumber);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
