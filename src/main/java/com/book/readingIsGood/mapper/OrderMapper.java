package com.book.readingIsGood.mapper;

import com.book.readingIsGood.dto.order.OrderDTO;
import com.book.readingIsGood.model.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO mapToOrderDTO(Order order);

    Order mapToOrder(OrderDTO orderDTO);

    List<OrderDTO> mapToOrderDTOList(List<Order> orderList);
}
