package com.book.readingIsGood.repository;

import com.book.readingIsGood.model.book.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {


}
