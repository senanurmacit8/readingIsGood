package com.book.readingIsGood.service.book;

import com.book.readingIsGood.dto.book.BookDTO;
import com.book.readingIsGood.mapper.BookMapper;
import com.book.readingIsGood.model.book.Book;
import com.book.readingIsGood.repository.BookRepository;
import com.book.readingIsGood.service.book.impl.BookServiceImpl;
import com.mongodb.MongoException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
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

        String actualResponse = service.updateBookStock("id",2);

        Assert.assertEquals("success", actualResponse);
    }

    @Test(expected = MongoException.class)
    public void whenUpdateBookStock_thenThrowsException() throws Exception {
        Mockito.when(repository.existsById(anyString())).thenThrow(new MongoException(" Exception"));
        service.updateBookStock("id",2);
    }

    @Test()
    public void whenAddNewBook_thenReturnSuccessResponse() throws Exception {
        Mockito.when(mapper.mapToBook(any())).thenReturn(prepareBook().get(0));

        BookDTO bookDTO = expectedBookDTOList().get(0);
        String actualResponse = service.addNewBook(bookDTO);

        Assert.assertEquals("success", actualResponse);
    }

    @Test(expected = MongoException.class)
    public void whenAddNewBook_thenThrowsException() throws Exception {
        Mockito.when(repository.save(any())).thenThrow(new MongoException(" Exception"));
        BookDTO bookDTO = expectedBookDTOList().get(0);
        service.addNewBook(bookDTO);
    }

    @Test()
    public void whenAddNewBook_thenReturnFailedResponse() throws Exception {
        Mockito.when(mapper.mapToBook(any())).thenReturn(prepareBook().get(0));

        BookDTO bookDTO = expectedBookDTOList().get(0);
        String actualResponse = service.addNewBook(bookDTO);

        Assert.assertEquals("success", actualResponse);
    }

    @Test()
    public void whenGetBookById_thenReturnBookDTO() {
        List<Book> bookList = prepareBook();

        Mockito.when(repository.findById(anyString())).thenReturn(bookList.stream().findAny());
        Mockito.when(mapper.mapToBookDTO(any())).thenReturn(expectedBookDTOList().get(0));

        BookDTO actualResponse = service.getBookById("id");

        Assert.assertEquals(expectedBookDTOList().get(0), actualResponse);
    }

    @Test()
    public void whenGetBookById_thenReturnNull() {
        Mockito.when(repository.findById(anyString())).thenReturn(null);

        BookDTO actualResponse = service.getBookById("id");

        Assert.assertNull(actualResponse);
    }

    @Test
    public void whenGetAllBooks_thenReturnListNull() {
        Mockito.when(repository.findAll()).thenReturn(null);

        List<BookDTO> actualResponse = service.getAllBooks();

        Assert.assertTrue(CollectionUtils.isEmpty(actualResponse));
    }

    @Test
    public void whenGetAllBooks_thenRetrievedBookDTOs() {
        Mockito.when(repository.findAll()).thenReturn(prepareBook());
        Mockito.when(mapper.mapToBookDTOList(anyList())).thenReturn(expectedBookDTOList());

        List<BookDTO> actualResponse = service.getAllBooks();
        Assert.assertEquals(expectedBookDTOList(), actualResponse);
    }

    private List<Book> prepareBook() {
        Book book = new Book("id", "bookName", "bookAuthor", "category", 3.4, 1);
        return Collections.singletonList(book);
    }

    private List<BookDTO> expectedBookDTOList() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setCount(1);
        bookDTO.setName("bookName");
        bookDTO.setAuthor("bookAuthor");
        bookDTO.setAmount(3.4);
        return Collections.singletonList(bookDTO);
    }
}
