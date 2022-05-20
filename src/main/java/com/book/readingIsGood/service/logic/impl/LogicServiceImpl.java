package com.book.readingIsGood.service.logic.impl;

import com.book.readingIsGood.dto.book.BookDTO;
import com.book.readingIsGood.dto.constants.StatusEnum;
import com.book.readingIsGood.dto.customer.CustomerDTO;
import com.book.readingIsGood.dto.order.OrderDTO;
import com.book.readingIsGood.model.customer.ListCustomerOrdersAndCustomerResponse;
import com.book.readingIsGood.model.customer.ListCustomerOrdersResponse;
import com.book.readingIsGood.service.book.BookService;
import com.book.readingIsGood.service.customer.CustomerService;
import com.book.readingIsGood.service.logic.LogicService;
import com.book.readingIsGood.service.order.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class LogicServiceImpl implements LogicService {

    private OrderService orderService;
    private BookService bookService;
    private CustomerService customerService;

    public LogicServiceImpl(OrderService orderService,
                            BookService bookService,
                            CustomerService customerService) {
        this.orderService = orderService;
        this.bookService = bookService;
        this.customerService = customerService;
    }

    @Transactional
    public ResponseEntity createNewOrder(OrderDTO orderDTO) throws Exception {
        log.info("createNewOrder method called.");
        orderDTO.setStatus(StatusEnum.ORDERED.toString());

        ResponseEntity isSucceeds = orderService.createNewOrder(orderDTO);

        if (isSucceeds.getStatusCode().is2xxSuccessful()) {
            BookDTO bookDTO = bookService.getBookById(orderDTO.getBookId());
            if (null != bookDTO) {
                bookService.updateBookStock(orderDTO.getBookId(), bookDTO.getCount() - 1);
            }
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity orderIsDelivered(OrderDTO orderDTO) throws Exception {
        log.info("orderIsDelivered method called.");
        orderService.updateOrderStatus(orderDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ListCustomerOrdersAndCustomerResponse listCustomerOrdersAndCustomerByCustomerId(String customerId) {
        log.info("getCustomerOrders method called.");
        ListCustomerOrdersAndCustomerResponse customerOrdersListResponse = null;

        CustomerDTO response = customerService.findCustomerByCustomerId(customerId);

        if (null != response) {

            customerOrdersListResponse.setCustomerDTO(response);

            List<String> orderIdList = Arrays.stream(response.getOrders().split(",")).collect(Collectors.toList());

            List<OrderDTO> orderList = orderIdList.parallelStream()
                    .map(orderId -> orderService.getOrderById(orderId)).collect(Collectors.toList());

            customerOrdersListResponse.setOrderDTOList(orderList);

        }
        return customerOrdersListResponse;

    }

    public ListCustomerOrdersResponse listCustomerOrdersByCustomerId(String customerId) {
        log.info("listCustomerOrdersByCustomerId method called.");
        ListCustomerOrdersResponse customerOrdersListResponse = null;

        List<OrderDTO> responseList = orderService.listOrdersByCustomerId(customerId);

        if (!CollectionUtils.isEmpty(responseList)) {
            customerOrdersListResponse.setOrderDTOList(responseList);
        }

        return customerOrdersListResponse;
    }
}

