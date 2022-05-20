package com.book.readingIsGood.repository;

import com.book.readingIsGood.model.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    // @Query("{'orderDate' : :#{#orderDate}, 'deliveredDate' : :#{#deliveredDate}}")
    // List<Order> findNamedParameters(@Param("orderDate") LocalDate orderDate, @Param("deliveredDate") LocalDate deliveredDate);

    List<Order> getAllByCustomerId(String customerId);

    List<Order> getAllByOrderDateAndDeliveredDate(LocalDate orderDate, LocalDate deliveredDate);
}
