package com.book.readingIsGood.service.logic;

import com.book.readingIsGood.dto.order.OrderDTO;
import com.book.readingIsGood.model.customer.ListCustomerOrdersAndCustomerResponse;
import com.book.readingIsGood.model.customer.ListCustomerOrdersResponse;
import org.springframework.http.ResponseEntity;

public interface LogicService {

    ResponseEntity createNewOrder(OrderDTO orderDTO) throws Exception;

    ResponseEntity orderIsDelivered(OrderDTO orderDTO) throws Exception;

    ListCustomerOrdersAndCustomerResponse listCustomerOrdersAndCustomerByCustomerId(String customerId);

    ListCustomerOrdersResponse listCustomerOrdersByCustomerId(String customerId);
}
