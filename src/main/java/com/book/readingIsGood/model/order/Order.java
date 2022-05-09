package com.book.readingIsGood.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "order")
@AllArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    private String id;

    private String bookId;
    private String customerId;
    private String status;
    private LocalDate orderDate;
    private LocalDate deliveredDate;
}
