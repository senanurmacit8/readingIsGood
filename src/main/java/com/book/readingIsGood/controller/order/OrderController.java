package com.book.readingIsGood.controller.order;

import com.book.readingIsGood.dto.order.OrderDTO;
import com.book.readingIsGood.service.order.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController("/order")
public class OrderController {

    private OrderService service;

    public OrderController(OrderService service){
        this.service= service;
    }

    @GetMapping("/getAllOrders")
    public List<OrderDTO> getAllOrders(){
        List<OrderDTO> orderDTOList = service.getAllOrders();
        return orderDTOList;
    }

    @PostMapping("/getOrderById")
    public OrderDTO getOrderById(@RequestParam  String orderId){
       return service.getOrderById(orderId);
    }

    @GetMapping("/listOrdersByOrderDateAndDeliveredDate")
    public List<OrderDTO> listOrdersByOrderDateAndDeliveredDate(@RequestParam LocalDate startDate, LocalDate endDate){
        return service.listOrdersByOrderDateAndDeliveredDate(startDate,endDate);
    }
}
