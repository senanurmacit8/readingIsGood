package com.book.readingIsGood.service.customer;

import com.book.readingIsGood.dto.customer.CustomerDTO;

import java.util.List;

public interface CustomerService {

     List<CustomerDTO> getAllCustomers();

     String createNewCustomer(CustomerDTO customerDTO);

     List<String> getCustomerOrders(String customerId);

}
