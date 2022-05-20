package com.book.readingIsGood.model.customer;

import com.book.readingIsGood.dto.order.OrderDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ListCustomerOrdersResponse implements Serializable {
    List<OrderDTO> orderDTOList;
}
