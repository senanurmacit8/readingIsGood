package com.book.readingIsGood.service.logic.order;

import com.book.readingIsGood.dto.book.BookDTO;
import com.book.readingIsGood.dto.constants.StatusEnum;
import com.book.readingIsGood.dto.order.OrderDTO;
import com.book.readingIsGood.service.book.BookService;
import com.book.readingIsGood.service.order.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class OrderLogic {

    private OrderService orderService;
    private BookService bookService;

    public OrderLogic(OrderService orderService,
                      BookService bookService){
        this.orderService= orderService;
        this.bookService= bookService;
    }

    @Transactional
    public String createNewOrder(OrderDTO orderDTO) throws Exception {
        log.info("createNewOrder method called.");
        orderDTO.setStatus(StatusEnum.ORDERED.toString());

        String isSucces = orderService.createNewOrder(orderDTO);

        if("succes".equals(isSucces)){
            BookDTO bookDTO = bookService.getBookById(orderDTO.getBookId());
            if (null!= bookDTO){
                bookService.updateBookStock(orderDTO.getBookId(), bookDTO.getCount()-1);
            }
        }

        return "success";
    }

    @Transactional
    public String orderIsDelivered(OrderDTO orderDTO) throws Exception {
        log.info("orderIsDelivered method called.");
        orderService.updateOrderStatus(orderDTO);
        return "success";
    }
}

