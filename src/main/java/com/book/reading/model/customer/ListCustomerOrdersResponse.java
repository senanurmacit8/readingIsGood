package com.book.reading.model.customer;

import com.book.reading.dto.order.OrderInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ListCustomerOrdersResponse implements Serializable {
    List<OrderInfo> orderInfoList;
}
