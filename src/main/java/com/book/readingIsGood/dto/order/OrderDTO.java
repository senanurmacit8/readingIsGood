package com.book.readingIsGood.dto.order;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDTO {

    private String bookId;
    private String customerId;
    private String status;
    private LocalDate orderDate;
    private LocalDate deliveredDate;

}
