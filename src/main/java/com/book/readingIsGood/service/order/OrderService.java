package com.book.readingIsGood.service.order;

import com.book.readingIsGood.dto.order.OrderDTO;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

     String createNewOrder(OrderDTO orderDTO) throws Exception ;
     List<OrderDTO> getAllOrders();
     OrderDTO getOrderById(String orderId);
     List<OrderDTO> listOrdersByOrderDateAndDeliveredDate( LocalDate startDate, LocalDate endDate);
     String updateOrderStatus(OrderDTO orderDTO) throws Exception ;

    }
