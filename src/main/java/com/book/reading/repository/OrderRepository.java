package com.book.reading.repository;

import com.book.reading.model.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> getAllByCustomerId(String customerId);

    List<Order> getAllByOrderDateAndDeliveredDate(LocalDate orderDate, LocalDate deliveredDate);

}
