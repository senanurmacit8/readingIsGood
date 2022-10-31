package com.book.reading.service.order;

import com.book.reading.dto.order.OrderInfo;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    ResponseEntity createNewOrder(OrderInfo orderInfo) throws Exception;

    List<OrderInfo> getAllOrders();

    OrderInfo getOrderByOrderId(String orderId);

    List<OrderInfo> listOrdersByOrderDateAndDeliveredDate(LocalDate startDate, LocalDate endDate);

    ResponseEntity updateOrderStatus(OrderInfo orderInfo) throws Exception;

    List<OrderInfo> listOrdersByCustomerId(String customerId);

}
