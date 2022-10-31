package com.book.reading.service.book;

import com.book.reading.dto.book.BookInfo;
import com.book.reading.mapper.BookMapper;
import com.book.reading.model.book.Book;
import com.book.reading.repository.BookRepository;
import com.book.reading.service.book.impl.BookServiceImpl;
import com.mongodb.MongoException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    @InjectMocks
    BookServiceImpl service;
    @Mock
    BookRepository repository;
    @Mock
    BookMapper mapper;

    @Test()
    public void whenUpdateBookStock_thenReturnSuccessResponse() throws Exception {
        Mockito.when(repository.existsById(anyString())).thenReturn(true);
        List<Book> bookList = prepareBook();
        Mockito.when(repository.findById(anyString())).thenReturn(bookList.stream().findAny());
        ResponseEntity actualResponse = service.updateBookStock("id", 2);

        Assert.assertTrue(actualResponse.getStatusCode().is2xxSuccessful());
    }

    @Test(expected = MongoException.class)
    public void whenUpdateBookStock_thenThrowsException() throws Exception {
        Mockito.when(repository.existsById(anyString())).thenThrow(new MongoException(" Exception"));
        service.updateBookStock("id", 2);
    }

    @Test()
    public void whenAddNewBook_thenReturnSuccessResponse() throws Exception {
        Mockito.when(mapper.mapToBook(any())).thenReturn(prepareBook().get(0));

        BookInfo bookInfo = expectedBookDTOList().get(0);
        ResponseEntity actualResponse = service.addNewBook(bookInfo);

        Assert.assertTrue(actualResponse.getStatusCode().is2xxSuccessful());
    }

    @Test(expected = MongoException.class)
    public void whenAddNewBook_thenThrowsException() throws Exception {
        Mockito.when(repository.save(any())).thenThrow(new MongoException(" Exception"));
        BookInfo bookInfo = expectedBookDTOList().get(0);
        service.addNewBook(bookInfo);
    }

    @Test()
    public void whenAddNewBook_thenReturnFailedResponse() throws Exception {
        Mockito.when(mapper.mapToBook(any())).thenReturn(prepareBook().get(0));

        BookInfo bookInfo = expectedBookDTOList().get(0);
        ResponseEntity actualResponse = service.addNewBook(bookInfo);

        Assert.assertTrue(actualResponse.getStatusCode().is2xxSuccessful());
    }

    @Test()
    public void whenGetBookById_thenReturnBookDTO() {
        List<Book> bookList = prepareBook();

        Mockito.when(repository.findById(anyString())).thenReturn(bookList.stream().findAny());
        Mockito.when(mapper.mapToBookDTO(any())).thenReturn(expectedBookDTOList().get(0));

        BookInfo actualResponse = service.getBookById("id");

        Assert.assertEquals(expectedBookDTOList().get(0), actualResponse);
    }

    @Test()
    public void whenGetBookById_thenReturnNull() {
        Mockito.when(repository.findById(anyString())).thenReturn(null);

        BookInfo actualResponse = service.getBookById("id");

        Assert.assertNull(actualResponse);
    }

    @Test
    public void whenGetAllBooks_thenReturnListNull() {
        Mockito.when(repository.findAll()).thenReturn(null);

        List<BookInfo> actualResponse = service.getAllBooks();

        Assert.assertTrue(CollectionUtils.isEmpty(actualResponse));
    }

    @Test
    public void whenGetAllBooks_thenRetrievedBookDTOs() {
        Mockito.when(repository.findAll()).thenReturn(prepareBook());
        Mockito.when(mapper.mapToBookDTOList(anyList())).thenReturn(expectedBookDTOList());

        List<BookInfo> actualResponse = service.getAllBooks();
        Assert.assertEquals(expectedBookDTOList(), actualResponse);
    }

    private List<Book> prepareBook() {
        Book book = new Book("id","bookName","bookAuthor","category",3.4,1,1);
        return Collections.singletonList(book);
    }

    private List<BookInfo> expectedBookDTOList() {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setCount(1);
        bookInfo.setName("bookName");
        bookInfo.setAuthor("bookAuthor");
        bookInfo.setAmount(3.4);
        bookInfo.setVersion(1);
        return Collections.singletonList(bookInfo);
    }
}
