package com.book.readingIsGood.repository;

import com.book.readingIsGood.model.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order,String> {

    @Query("{'orderDate' : :#{#orderDate}, 'deliveredDate' : :#{#deliveredDate}}")
    List<Order> findNamedParameters(@Param("orderDate") LocalDate orderDate, @Param("deliveredDate") LocalDate deliveredDate);
}
