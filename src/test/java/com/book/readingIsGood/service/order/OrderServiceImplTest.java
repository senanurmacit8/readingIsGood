package com.book.readingIsGood.service.order;

import com.book.readingIsGood.dto.constants.StatusEnum;
import com.book.readingIsGood.dto.order.OrderDTO;
import com.book.readingIsGood.mapper.OrderMapper;
import com.book.readingIsGood.model.order.Order;
import com.book.readingIsGood.repository.OrderRepository;
import com.book.readingIsGood.service.order.impl.OrderServiceImpl;
import com.mongodb.MongoException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository repository;

    @Mock
    private OrderMapper mapper;

    @InjectMocks
    private OrderServiceImpl service;

    @Test
    public void whenUpdateOrderStatus_thenSuccessResponse() throws MongoException {
        Mockito.when(mapper.mapToOrder(any())).thenReturn(prepareOrder().get(0));
        Mockito.when(repository.findById(anyString())).thenReturn(prepareOrder().stream().findAny());

        ResponseEntity actualResponse = service.updateOrderStatus(expectedOrderDTOList().get(0));

        Assert.assertTrue(actualResponse.getStatusCode().is2xxSuccessful());
    }

    @Test(expected = MongoException.class)
    public void whenUpdateOrderStatus_thenThrowsException() throws MongoException {
        Mockito.when(mapper.mapToOrder(any())).thenReturn(prepareOrder().get(0));
        Mockito.when(repository.findById(anyString())).thenThrow(new MongoException("MongoException occurs."));

        service.updateOrderStatus(expectedOrderDTOList().get(0));
    }

    @Test
    public void whenListOrdersByOrderDateAndDeliveredDate_thenSuccessResponse() {
        Mockito.when(repository.getAllByOrderDateAndDeliveredDate(any(), any())).thenReturn(prepareOrder());
        Mockito.when(mapper.mapToOrderDTOList(any())).thenReturn(expectedOrderDTOList());

        List<OrderDTO> actualResponse =
                service.listOrdersByOrderDateAndDeliveredDate(LocalDate.now(), LocalDate.now());

        Assert.assertEquals(expectedOrderDTOList(), actualResponse);
    }

    @Test
    public void whenGetOrderById_thenSuccessResponse() {
        List<Order> order = prepareOrder();
        Mockito.when(repository.findById(anyString())).thenReturn(order.stream().findAny());
        Mockito.when(mapper.mapToOrderDTO(any())).thenReturn(expectedOrderDTOList().get(0));

        String orderId = "id";
        OrderDTO actualResponse = service.getOrderById(orderId);

        Assert.assertEquals(expectedOrderDTOList().get(0), actualResponse);
    }

    @Test
    public void whenGetOrderById_thenNullResponse() {
        Mockito.when(repository.findById(anyString())).thenReturn(null);

        String orderId = "id";
        OrderDTO actualResponse = service.getOrderById(orderId);

        Assert.assertNull(actualResponse);
    }

    @Test
    public void whenGetAllOrders_thenSuccessResponse() {
        Mockito.when(mapper.mapToOrderDTOList(any())).thenReturn(expectedOrderDTOList());
        Mockito.when(repository.findAll()).thenReturn(prepareOrder());

        List<OrderDTO> actualResponse = service.getAllOrders();

        Assert.assertEquals(expectedOrderDTOList(), actualResponse);
    }

    @Test
    public void whenCreateNewOrder_thenSuccessResponse() throws MongoException {
        Mockito.when(mapper.mapToOrder(any())).thenReturn(prepareOrder().get(0));
        ResponseEntity actualResponse = service.createNewOrder(expectedOrderDTOList().get(0));

        Assert.assertTrue(actualResponse.getStatusCode().is2xxSuccessful());
    }

    @Test(expected = MongoException.class)
    public void whenCreateNewOrder_thenThrowsException() throws MongoException {
        Mockito.when(repository.save(any())).thenThrow(new MongoException("MongoError occur!"));
        service.createNewOrder(expectedOrderDTOList().get(0));

    }

    private List<Order> prepareOrder() {
        Order order = new Order("id", "bookId", "customerId", StatusEnum.ORDERED.toString(),
                LocalDate.now(), LocalDate.now());
        return Collections.singletonList(order);
    }

    private List<OrderDTO> expectedOrderDTOList() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBookId("bookId");
        orderDTO.setStatus(StatusEnum.ORDERED.toString());
        orderDTO.setOrderDate(LocalDate.now());
        orderDTO.setDeliveredDate(LocalDate.now());
        return Collections.singletonList(orderDTO);
    }

}
