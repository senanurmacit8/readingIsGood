package com.book.reading.service.logic;

import com.book.reading.dto.order.OrderInfo;
import com.book.reading.model.customer.ListCustomerOrdersAndCustomerResponse;
import com.book.reading.model.customer.ListCustomerOrdersResponse;
import org.springframework.http.ResponseEntity;

public interface OrderBusinessService {

    ResponseEntity createNewOrder(OrderInfo orderInfo) throws Exception;

    ResponseEntity orderIsDelivered(OrderInfo orderInfo) throws Exception;

    ListCustomerOrdersAndCustomerResponse listCustomerOrdersAndCustomerByCustomerId(String customerId);

    ListCustomerOrdersResponse listCustomerOrdersByCustomerId(String customerId);
}
