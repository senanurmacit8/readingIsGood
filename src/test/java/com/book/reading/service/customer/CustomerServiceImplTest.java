package com.book.reading.service.customer;

import com.book.reading.dto.customer.CustomerInfo;
import com.book.reading.mapper.CustomerMapper;
import com.book.reading.model.customer.Customer;
import com.book.reading.repository.CustomerRepository;
import com.book.reading.service.customer.impl.CustomerServiceImpl;
import com.mongodb.MongoException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerServiceImpl service;

    @Test
    public void whenCreateNewCustomer_thenSuccessResponse() {
        List<Customer> customerList = prepareCustomer();
        Mockito.when(mapper.mapToCustomer(any())).thenReturn(customerList.get(0));

        ResponseEntity actualResponse = service.createNewCustomer(expectedCustomerDTOList().get(0));
        Assert.assertTrue(actualResponse.getStatusCode().is2xxSuccessful());
    }

    @Test(expected = MongoException.class)
    public void whenCreateNewCustomer_thenThrowsException() {
        Customer customer = new Customer(null, "customerName", "customerOrders");

        Mockito.when(mapper.mapToCustomer(any())).thenReturn(customer);
        Mockito.when(repository.save(any())).thenThrow(new MongoException("MongoException occur."));

        service.createNewCustomer(expectedCustomerDTOList().get(0));
    }

    @Test
    public void whenGetAllCustomers_thenRetrievedBookDTOs() {
        Mockito.when(repository.findAll()).thenReturn(prepareCustomer());
        Mockito.when(mapper.mapToCustomerDTOList(anyList())).thenReturn(expectedCustomerDTOList());

        List<CustomerInfo> actualResponse = service.getAllCustomers();
        Assert.assertEquals(expectedCustomerDTOList(), actualResponse);
    }

    private List<Customer> prepareCustomer() {
        Customer customer = new Customer("id", "customerName", "orders");
        return Collections.singletonList(customer);
    }

    private List<CustomerInfo> expectedCustomerDTOList() {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setName("customerName");
        customerInfo.setOrders("orders");
        return Collections.singletonList(customerInfo);
    }
}