package com.book.readingIsGood.service.customer;

import com.book.readingIsGood.dto.customer.CustomerDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    ResponseEntity createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO findCustomerByCustomerId(String customerId);
}
