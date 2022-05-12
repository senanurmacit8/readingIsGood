package com.book.readingIsGood.service.order.impl;

import com.book.readingIsGood.dto.constants.StatusEnum;
import com.book.readingIsGood.dto.order.OrderDTO;
import com.book.readingIsGood.mapper.OrderMapper;
import com.book.readingIsGood.model.order.Order;
import com.book.readingIsGood.repository.OrderRepository;
import com.book.readingIsGood.service.order.OrderService;
import com.mongodb.MongoException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public String createNewOrder(OrderDTO orderDTO) throws MongoException {
        log.info("createNewOrder method called");

        try {
            if (null != orderDTO) {
                repository.save(mapper.mapToOrder(orderDTO));
            }
        } catch (MongoException ex) {
            log.error(ex);
            throw new MongoException("MongoError occur!");
        }

        return "success";
    }

    public List<OrderDTO> getAllOrders() {
        log.info("getAllOrders method called");

        List<OrderDTO> orderDTOList = null;

        List<Order> orderList = repository.findAll();

        if (!CollectionUtils.isEmpty(orderList)) {
            orderDTOList = mapper.mapToOrderDTOList(orderList);
        }

        return orderDTOList;
    }

    public OrderDTO getOrderById(String orderId) {
        log.info("getOrderById method Called.");
        OrderDTO orderDTO = null;

        Optional<Order> response = repository.findById(orderId);

        if (null != response) {
            orderDTO = mapper.mapToOrderDTO(response.get());
        } else {
            log.info("There is no books");
        }

        return orderDTO;
    }

    public List<OrderDTO> listOrdersByOrderDateAndDeliveredDate(LocalDate startDate, LocalDate endDate) {
        log.info("listOrdersByOrderDateAndDeliveredDate method called.");
        List<OrderDTO> orderDTOList = null;

        List<Order> responseList = repository.findNamedParameters(startDate, endDate);
        if (!CollectionUtils.isEmpty(responseList)) {
            orderDTOList = mapper.mapToOrderDTOList(responseList);
        }
        return orderDTOList;
    }

    public String updateOrderStatus(OrderDTO orderDTO) throws MongoException {
        log.info("updateOrderStatus method called");

        try {
            if (null != orderDTO) {
                String orderId = mapper.mapToOrder(orderDTO).getId();

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

        return "success";
    }

}
