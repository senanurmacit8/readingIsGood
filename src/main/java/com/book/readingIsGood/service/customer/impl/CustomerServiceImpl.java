package com.book.readingIsGood.service.customer.impl;

import com.book.readingIsGood.dto.customer.CustomerDTO;
import com.book.readingIsGood.mapper.CustomerMapper;
import com.book.readingIsGood.model.customer.Customer;
import com.book.readingIsGood.repository.CustomerRepository;
import com.book.readingIsGood.service.customer.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;
    private CustomerMapper mapper;


    public CustomerServiceImpl (CustomerRepository repository,
                                CustomerMapper mapper){
        this.repository= repository;
        this.mapper= mapper;
    }

    public List<CustomerDTO> getAllCustomers(){
        log.info("getAllCustomers method called.");
        List<CustomerDTO> customerDTOList = null;

        List<Customer> responseList = repository.findAll();
        if(!CollectionUtils.isEmpty(responseList)){
            customerDTOList = mapper.mapToCustomerDTOList(responseList);
        }

        return customerDTOList;
    }

    public String createNewCustomer( CustomerDTO customerDTO){
        log.info("createNewCustomer method called. ");
        repository.save(mapper.mapToCustomer(customerDTO));

        return "success";
    }

    public List<String> getCustomerOrders(String customerId){
        log.info("getCustomerOrders method called.");
        List<String> customerOrdersList = null;

        List<Customer> responseList = (List<Customer>) repository.findAllById(Collections.singleton(customerId));

        if (!CollectionUtils.isEmpty(responseList)){
            customerOrdersList =  responseList.stream().map(customer -> customer.getOrders()).collect(Collectors.toList());
        }

        return customerOrdersList;
    }
}
