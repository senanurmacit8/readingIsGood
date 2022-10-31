package com.book.reading.controller.order;

import com.book.reading.dto.order.OrderInfo;
import com.book.reading.service.order.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * @author senanurmacit
 * @version 1.1
 * @since 1.1
 */
@RestController("/order")
public class OrderController {

    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    /**
     * This method created for list all orders
     *
     * @return
     */
    @GetMapping("/getAllOrders")
    public List<OrderInfo> getAllOrders() {
        List<OrderInfo> orderInfoList = service.getAllOrders();
        return orderInfoList;
    }

    /**
     * This method created for create new order
     *
     * @return
     */
    @GetMapping("/createNewOrder")
    public OrderInfo createNewOrder(OrderInfo orderInfo) throws Exception {
        ResponseEntity responseEntity = service.createNewOrder(orderInfo);
        if ("200".equals(responseEntity.getStatusCodeValue()))
            return orderInfo;
        return null;
    }

    /**
     * This method is created for get order
     * by Id
     *
     * @param orderId
     * @return
     */
    @PostMapping("/getOrderById")
    public OrderInfo getOrderById(@RequestParam String orderId) {
        return service.getOrderByOrderId(orderId);
    }

    /**
     * This method is created for list orders
     * by order date and order delivered date
     *
     * @param startDate
     * @param endDate
     * @return List<OrderDTO>
     * @see OrderInfo
     */
    @GetMapping("/listOrdersByOrderDateAndDeliveredDate")
    public List<OrderInfo> listOrdersByOrderDateAndDeliveredDate(@RequestParam LocalDate startDate, LocalDate endDate) {
        return service.listOrdersByOrderDateAndDeliveredDate(startDate, endDate);
    }


}
