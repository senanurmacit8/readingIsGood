package com.book.readingIsGood.repository;

import com.book.readingIsGood.model.book.Book;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookRepository extends MongoRepository<Book, String> {

}
