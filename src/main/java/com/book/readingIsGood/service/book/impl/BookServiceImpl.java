package com.book.readingIsGood.service.book.impl;

import com.book.readingIsGood.dto.book.BookDTO;
import com.book.readingIsGood.mapper.BookMapper;
import com.book.readingIsGood.model.book.Book;
import com.book.readingIsGood.repository.BookRepository;
import com.book.readingIsGood.service.book.BookService;
import com.mongodb.MongoWriteConcernException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private BookMapper mapper;


    public BookServiceImpl(BookRepository repository,
                          BookMapper mapper){
        this.bookRepository= repository;
        this.mapper= mapper;
    }

    public List<BookDTO> getAllBooks(){
        log.info("getAllBooks method Called.");
        List<BookDTO> bookDTOList = null;

        List<Book> responseList = bookRepository.findAll();

        if(!CollectionUtils.isEmpty(responseList)){
            bookDTOList = mapper.mapToBookDTOList(responseList);
        }else {
            log.info("There is no books");
        }

        return bookDTOList;
    }

    public BookDTO getBookById(String bookId){
        log.info("getBookById method Called.");
        BookDTO bookDTO = null;

        Optional<Book> response = bookRepository.findById(bookId);

        if(null!= response){
            bookDTO = mapper.mapToBookDTO(response.get());
        }else {
            log.info("There is no books");
        }

        return bookDTO;
    }

    public String addNewBook( BookDTO bookDTO) throws Exception {
        log.info("addNewBook method called");

        try{
            if(null!= bookDTO){
                bookRepository.save(mapper.mapToBook(bookDTO));
            }
        }catch(MongoWriteConcernException ex){
            log.error(ex);
            throw new Exception("Book already exists!");
        }

        return "success";
    }

    public String updateBookStock(String bookId, Integer stockNumber) throws Exception {
        log.info("updateBookStock method called");

        try{
            if(null!= bookId && null!= stockNumber){
                boolean isExist = bookRepository.existsById(bookId);
                if (isExist){
                    Book book = bookRepository.findById(bookId).get();
                    book.setCount(stockNumber);
                    bookRepository.save(book);
                }
            }
        }catch(MongoWriteConcernException ex){
            log.error(ex);
            throw new Exception("Book already exists!");
        }

        return "success";
    }

}
