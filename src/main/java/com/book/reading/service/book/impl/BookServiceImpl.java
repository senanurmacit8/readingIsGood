package com.book.reading.service.book.impl;

import com.book.reading.dto.book.BookInfo;
import com.book.reading.mapper.BookMapper;
import com.book.reading.model.book.Book;
import com.book.reading.repository.BookRepository;
import com.book.reading.service.book.BookService;
import com.mongodb.MongoException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author senanurmacit
 * @version 1.1
 * @since 1.1
 */
@Service
@Log4j2
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private BookMapper mapper;

    public BookServiceImpl(BookRepository repository,
                           BookMapper mapper) {
        this.bookRepository = repository;
        this.mapper = mapper;
    }

    /**
     * This method created fro getting all Books
     *
     * @return List<BookDTO>
     * @see BookInfo
     */
    public List<BookInfo> getAllBooks() {
        log.info("getAllBooks method Called.");
        List<BookInfo> bookInfoList = null;

        List<Book> responseList = bookRepository.findAll();

        if (!CollectionUtils.isEmpty(responseList)) {
            bookInfoList = mapper.mapToBookDTOList(responseList);
        } else {
            log.info("There is no books");
        }

        return bookInfoList;
    }

    /**
     * This method created for get Book by Id
     * bookId argumant must be String value
     *
     * @param bookId
     * @return BookDTO
     * @see BookInfo
     */
    public BookInfo getBookById(String bookId) {
        log.info("getBookById method Called.");
        BookInfo bookInfo = null;

        try {
            Optional<Book> response = bookRepository.findById(bookId);
            bookInfo = mapper.mapToBookDTO(response.get());

        } catch (MongoException mongoException) {
            log.error(mongoException.getMessage());
            return new BookInfo();
        }

        return bookInfo;
    }

    /**
     * This method created for add new book to database
     *
     * @param bookInfo
     * @return ResponseEntity
     * @throws Exception
     * @see ResponseEntity
     */
    public ResponseEntity addNewBook(BookInfo bookInfo) throws Exception {
        log.info("addNewBook method called");

        try {
            if (null != bookInfo) {
                bookRepository.save(mapper.mapToBook(bookInfo));
            }
        } catch (MongoException ex) {
            log.error(ex);
            throw new MongoException("Book already exists!");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateBookStock(String bookId, String author, Integer stockNumber) throws OptimisticLockingFailureException {
        try {
            Optional<Book> book = bookRepository.findById(bookId);

            Integer countOfBooks = book.get().getCount();

            boolean isCountOfBooksEnough = countOfBooks.compareTo(stockNumber) == 0;

            if (!isCountOfBooksEnough) {
                log.error("There is no enough book stock for sale! ");
                return new ResponseEntity<>(HttpStatus.valueOf("NOT ENOUGH COUNT IN STORE!.."));
            } else {
                countOfBooks = countOfBooks - stockNumber;
                Book updatedBook = new Book(bookId, book.get().getName(), author, book.get().getCategory(), book.get().getAmount(), countOfBooks);
                bookRepository.save(updatedBook);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (MongoException ex) {
            throw new MongoException("Book already exists!");
        }
    }

    /**
     * This method created for update delivered book orders
     * When book is going to deliver this process had been started
     * After delivered orders it will be checked from UI and status will be delivered
     *
     * @param bookId
     * @param stockNumber
     * @return
     * @throws Exception
     */

    @Transactional(readOnly = true)
    public ResponseEntity updateBookStock(String bookId, Integer stockNumber) throws Exception {
        log.info("updateBookStock method called");

        try {
            if (null != bookId && null != stockNumber) {
                boolean isExist = bookRepository.existsById(bookId);
                if (isExist) {
                    Book book = bookRepository.findById(bookId).get();
                    book.setCount(stockNumber);
                    bookRepository.save(book);
                }
            }
        } catch (MongoException ex) {
            log.error(ex);
            throw new MongoException("Book not found!");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * This method created for update ordered(-) or added(+) book count
     * with optimistic locking
     * if it is not exist it will throws "Not found" exception
     *
     * @param bookName
     * @param author
     * @param stockNumber
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateBookStock(String bookName, String author, int stockNumber) {
        Book book = bookRepository.findByNameAndAuthor(bookName, author);
        if (Objects.isNull(book)) {
            throw (new EntityNotFoundException());
        }
        book.setCount(stockNumber);
    }

}
