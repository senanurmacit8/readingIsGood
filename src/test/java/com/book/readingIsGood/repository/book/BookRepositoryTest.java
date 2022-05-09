package com.book.readingIsGood.repository.book;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {
/*
    @Autowired
    private BookRepository bookRepository;

    @Before()
    public void setUp() throws Exception {
        Book book = new Book(null,"Suç ve Ceza", "Dostoyesky","clasic"
                ,0.0);
        assertNull(book.getId());
        this.bookRepository.save(book);
        assertNotNull(book.getId());
    }

 */
   /*
    @Test
    public void testFetchData(){
        Book book = bookRepository.findBookItemsByName("Suç ve Ceza");
        assertNotNull(book);
        Assert.assertEquals("Dostoyevsky", book.getAuthor());

        Iterable<Book> bookItemList = bookRepository.findAll();
        int count = 0;
        for(Book p : bookItemList){
            count++;
        }
        Assert.assertEquals(count, 1);
    }

    */

}
