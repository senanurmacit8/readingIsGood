package com.book.reading.service.customer;

import com.book.reading.dto.customer.CustomerInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    List<CustomerInfo> getAllCustomers();

    ResponseEntity createNewCustomer(CustomerInfo customerInfo);

    CustomerInfo findCustomerByCustomerId(String customerId);
}
