package com.book.readingIsGood.service.order;

import com.book.readingIsGood.dto.order.OrderDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    ResponseEntity createNewOrder(OrderDTO orderDTO) throws Exception;

    List<OrderDTO> getAllOrders();

    OrderDTO getOrderById(String orderId);

    List<OrderDTO> listOrdersByOrderDateAndDeliveredDate(LocalDate startDate, LocalDate endDate);

    ResponseEntity updateOrderStatus(OrderDTO orderDTO) throws Exception;

    List<OrderDTO> listOrdersByCustomerId(String customerId);

}
