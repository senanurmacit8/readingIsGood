package com.book.reading.dto.order;

import com.book.reading.dto.book.BookInfo;
import com.book.reading.dto.customer.CustomerInfo;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderInfo {

    private List<BookInfo> bookInfoList;
    private CustomerInfo customerId;
    private String status;
    private LocalDate orderDate;
    private LocalDate deliveredDate;

}
