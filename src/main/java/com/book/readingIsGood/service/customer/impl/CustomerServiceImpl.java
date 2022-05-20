package com.book.readingIsGood.service.customer.impl;

import com.book.readingIsGood.dto.customer.CustomerDTO;
import com.book.readingIsGood.mapper.CustomerMapper;
import com.book.readingIsGood.model.customer.Customer;
import com.book.readingIsGood.repository.CustomerRepository;
import com.book.readingIsGood.service.customer.CustomerService;
import com.mongodb.MongoException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;
    private CustomerMapper mapper;


    public CustomerServiceImpl(CustomerRepository repository,
                               CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CustomerDTO> getAllCustomers() {
        log.info("getAllCustomers method called.");
        List<CustomerDTO> customerDTOList = null;

        List<Customer> responseList = repository.findAll();
        if (!CollectionUtils.isEmpty(responseList)) {
            customerDTOList = mapper.mapToCustomerDTOList(responseList);
        }

        return customerDTOList;
    }

    public ResponseEntity createNewCustomer(CustomerDTO customerDTO) throws MongoException {
        log.info("createNewCustomer method called.");

        try {
            Customer customer = mapper.mapToCustomer(customerDTO);

            if (!Objects.isNull(customerDTO) && Objects.isNull(customer.getId())) {
                repository.save(customer);
            }
        } catch (MongoException mongoException) {
            log.error("MongoException occur.", mongoException);
            throw new MongoException("MongoException occur.");
        }

        return new ResponseEntity(HttpStatus.OK);
    }


    public CustomerDTO findCustomerByCustomerId(String customerId) {
        log.info("findCustomerByCustomerId method called.");

        Optional<Customer> response = repository.findById(customerId);

        if (null != response) {
            return mapper.mapToCustomerDTO(response.stream().findAny().get());
        }
        return null;
    }

}
