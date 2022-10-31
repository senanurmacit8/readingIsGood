package com.book.reading.mapper;

import com.book.reading.dto.order.OrderInfo;
import com.book.reading.model.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderInfo mapToOrderDTO(Order order);

    Order mapToOrder(OrderInfo orderInfo);

    List<OrderInfo> mapToOrderDTOList(List<Order> orderList);
}
