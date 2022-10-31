package com.book.reading.model.order;

import com.book.reading.model.book.Book;
import com.book.reading.model.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "order")
@AllArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    private String id;

    private List<Book> bookId;
    private Customer customerId;
    private String status;
    private LocalDate orderDate;
    private LocalDate deliveredDate;

}
