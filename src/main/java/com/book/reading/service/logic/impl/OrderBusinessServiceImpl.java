package com.book.reading.service.logic.impl;

import com.book.reading.dto.book.BookInfo;
import com.book.reading.dto.constants.StatusEnum;
import com.book.reading.dto.customer.CustomerInfo;
import com.book.reading.dto.order.OrderInfo;
import com.book.reading.model.customer.ListCustomerOrdersAndCustomerResponse;
import com.book.reading.model.customer.ListCustomerOrdersResponse;
import com.book.reading.service.book.BookService;
import com.book.reading.service.customer.CustomerService;
import com.book.reading.service.logic.OrderBusinessService;
import com.book.reading.service.order.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author senanurmacit
 * @version 1.1
 * @since Version 1.1
 */
@Service
@Log4j2
public class OrderBusinessServiceImpl implements OrderBusinessService {

    private OrderService orderService;
    private BookService bookService;
    private CustomerService customerService;

    public OrderBusinessServiceImpl(OrderService orderService,
                                    BookService bookService,
                                    CustomerService customerService) {
        this.orderService = orderService;
        this.bookService = bookService;
        this.customerService = customerService;
    }

    /**
     * This method created to create new Order
     *
is      * in this method : first customer select a book and push order button
     * Sepetindeki tüm kitaplar için order a basıldığı anda select sorgusu çalışmalı
     * ilk select sorgusu sonrası o kitap için select sorgusu lock lanıcak
     *
     *
     * @param orderInfo
     * @return
     * @throws Exception
     */

    public ResponseEntity createNewOrder(OrderInfo orderInfo) throws Exception {
        log.info("createNewOrder method called.");

        orderInfo.setStatus(StatusEnum.ORDERED.toString());

        if (null!= orderInfo && !CollectionUtils.isEmpty(orderInfo.getBookInfoList())){
            for (BookInfo bookInfo : orderInfo.getBookInfoList()){
                this.updateBookStock(bookInfo.getName(),bookInfo.getAuthor(),bookInfo.getCount());
            }

        }

        ResponseEntity isSucceeds = orderService.createNewOrder(orderInfo);

        if (isSucceeds.getStatusCode().is2xxSuccessful()) {
            //BookInfo bookInfo = bookService.getBookById(orderInfo.getBookId().);
           // if (null != bookInfo) {
               // bookService.updateBookStock(orderInfo.getBookId(), bookInfo.getCount() - 1);
           // }
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * This method created for set order status
     * id order is delivered order status will be change as delivered
     *
     * @param orderInfo
     * @return
     * @throws Exception
     */
    @Transactional
    public ResponseEntity orderIsDelivered(OrderInfo orderInfo) throws Exception {
        log.info("orderIsDelivered method called.");
        orderService.updateOrderStatus(orderInfo);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * This method was created for listing customerOrders and Customer Info
     * By customer Id from Customer document via customer service
     *
     * @param customerId
     * @return ListCustomerOrdersAndCustomerResponse
     * @see ListCustomerOrdersAndCustomerResponse
     */
    public ListCustomerOrdersAndCustomerResponse listCustomerOrdersAndCustomerByCustomerId(String customerId) {
        log.info("getCustomerOrders method called.");
        ListCustomerOrdersAndCustomerResponse customerOrdersListResponse = null;

        CustomerInfo response = customerService.findCustomerByCustomerId(customerId);

        if (null != response) {

            customerOrdersListResponse.setCustomerInfo(response);

            List<String> orderIdList = Arrays.stream(response.getOrders().split(",")).collect(Collectors.toList());

            List<OrderInfo> orderList = orderIdList.parallelStream()
                    .map(orderId -> orderService.getOrderByOrderId(orderId)).collect(Collectors.toList());

            customerOrdersListResponse.setOrderInfoList(orderList);

        }
        return customerOrdersListResponse;

    }

    /**
     * This method was created for listing customer orders Info
     * By CustomerId from Customer document in DBwas
     * via customer service
     *
     * @param customerId
     * @return ListCustomerOrdersResponse
     * @see ListCustomerOrdersResponse
     */
    public ListCustomerOrdersResponse listCustomerOrdersByCustomerId(String customerId) {
        log.info("listCustomerOrdersByCustomerId method called.");
        ListCustomerOrdersResponse customerOrdersListResponse = null;

        List<OrderInfo> responseList = orderService.listOrdersByCustomerId(customerId);

        if (!CollectionUtils.isEmpty(responseList)) {
            customerOrdersListResponse.setOrderInfoList(responseList);
        }

        return customerOrdersListResponse;
    }

    @Transactional(readOnly = true)
    public void updateBookStock(String bookId,String author, int stockNumber) {
        try {
            bookService.updateBookStock(bookId,author, stockNumber);
        } catch (ObjectOptimisticLockingFailureException e) {
            log.warn("Somebody has already updated the stockNumber for item:{} in concurrent transaction. Will try again...", bookId);
            bookService.updateBookStock(bookId, author,stockNumber);
        }
    }
}

