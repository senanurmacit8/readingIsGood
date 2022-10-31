package com.book.reading.service.order.impl;

import com.book.reading.dto.constants.StatusEnum;
import com.book.reading.dto.order.OrderInfo;
import com.book.reading.mapper.OrderMapper;
import com.book.reading.model.order.Order;
import com.book.reading.repository.OrderRepository;
import com.book.reading.service.order.OrderService;
import com.mongodb.MongoException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The class is created for Order Service methods.
 * Currently reachable for CRUD processes from repository.
 * Entities are converted as a DTO.
 *
 * @author senanurmacit
 * @since Version 1.1
 * @version 1.1
 */

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    private OrderRepository repository;
    private OrderMapper mapper;

    public OrderServiceImpl(OrderRepository repository,
                            OrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * The method used for creating the new order.
     * The orderDTO argument must specify an absolute all fields.
     * Before create order entity convert order object to entity.
     * </p>
     * @param orderInfo
     * @return
     * @throws MongoException
     */
    public ResponseEntity createNewOrder(OrderInfo orderInfo) throws MongoException {
        log.info("createNewOrder method called");

        try {
            if (null != orderInfo) {
                repository.save(mapper.mapToOrder(orderInfo));
            }
        } catch (MongoException ex) {
            log.error(ex);
            throw new MongoException("MongoError occur!");
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    public List<OrderInfo> getAllOrders() {
        log.info("getAllOrders method called");

        List<OrderInfo> orderInfoList = null;

        List<Order> orderList = repository.findAll();

        if (!CollectionUtils.isEmpty(orderList)) {
            orderInfoList = mapper.mapToOrderDTOList(orderList);
        }

        return orderInfoList;
    }

    public OrderInfo getOrderByOrderId(String orderId) {
        log.info("getOrderById method Called.");
        OrderInfo orderInfo = null;

        Optional<Order> response = repository.findById(orderId);

        if (null != response) {
            orderInfo = mapper.mapToOrderDTO(response.get());
        } else {
            log.info("There is no books");
        }

        return orderInfo;
    }

    public List<OrderInfo> listOrdersByOrderDateAndDeliveredDate(LocalDate startDate, LocalDate endDate) {
        log.info("listOrdersByOrderDateAndDeliveredDate method called.");
        List<OrderInfo> orderInfoList = null;

        List<Order> responseList = repository.getAllByOrderDateAndDeliveredDate(startDate, endDate);
        if (!CollectionUtils.isEmpty(responseList)) {
            orderInfoList = mapper.mapToOrderDTOList(responseList);
        }
        return orderInfoList;
    }

    public ResponseEntity updateOrderStatus(OrderInfo orderInfo) throws MongoException {
        log.info("updateOrderStatus method called");

        try {
            if (null != orderInfo) {
                String orderId = mapper.mapToOrder(orderInfo).getId();

                Order order = repository.findById(orderId).get();
                if (null != order.getId()) {
                    order.setStatus(StatusEnum.DELIVERED.toString());
                    repository.save(order);
                }
            }
        } catch (MongoException ex) {
            log.error(ex);
            throw new MongoException("MongoException occurs.");
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    public List<OrderInfo> listOrdersByCustomerId(String customerId) {
        log.info("listOrdersByCustomerId method Called.");
        List<OrderInfo> orderInfo = null;

        List<Order> responseList = repository.getAllByCustomerId(customerId);

        if (!CollectionUtils.isEmpty(responseList)) {
            orderInfo = mapper.mapToOrderDTOList(responseList);
        } else {
            log.info("There is no customers orders ");
        }

        return orderInfo;
    }

}
