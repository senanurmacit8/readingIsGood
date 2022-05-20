package com.book.readingIsGood.model.customer;

import com.book.readingIsGood.dto.customer.CustomerDTO;
import com.book.readingIsGood.dto.order.OrderDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ListCustomerOrdersAndCustomerResponse implements Serializable {
    CustomerDTO customerDTO;
    List<OrderDTO> orderDTOList;
}
