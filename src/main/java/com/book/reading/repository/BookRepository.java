package com.book.reading.repository;

import com.book.reading.model.book.Book;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAllByIdIn(List<String> bookIdList);

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Book findByNameAndAuthor(String name,String author);

}
