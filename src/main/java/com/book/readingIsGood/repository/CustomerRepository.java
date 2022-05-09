package com.book.readingIsGood.repository;

import com.book.readingIsGood.model.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {
}
