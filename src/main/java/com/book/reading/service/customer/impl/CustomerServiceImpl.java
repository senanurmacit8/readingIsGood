package com.book.reading.service.customer.impl;

import com.book.reading.dto.customer.CustomerInfo;
import com.book.reading.mapper.CustomerMapper;
import com.book.reading.model.customer.Customer;
import com.book.reading.repository.CustomerRepository;
import com.book.reading.service.customer.CustomerService;
import com.mongodb.MongoException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author senanurmacit
 * @since Version 1.1
 * @version 1.1
 */
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

    /**
     *This method created fro get All Customers as a Customer DTO
     * There is no parameter
     *
     * @return
     */
    public List<CustomerInfo> getAllCustomers() {
        log.info("getAllCustomers method called.");
        List<CustomerInfo> customerInfoList = null;

        List<Customer> responseList = repository.findAll();
        if (!CollectionUtils.isEmpty(responseList)) {
            customerInfoList = mapper.mapToCustomerDTOList(responseList);
        }

        return customerInfoList;
    }

    /**
     *This method created for create new customer
     *
     * @param customerInfo
     * @return
     * @throws MongoException
     */
    public ResponseEntity createNewCustomer(CustomerInfo customerInfo) throws MongoException {
        log.info("createNewCustomer method called.");

        try {
            Customer customer = mapper.mapToCustomer(customerInfo);

            if (!Objects.isNull(customerInfo) && Objects.isNull(customer.getId())) {
                repository.save(customer);
            }
        } catch (MongoException mongoException) {
            log.error("MongoException occur.", mongoException);
            throw new MongoException("MongoException occur.");
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Returns an CustomerDTO object that can then be painted on the screen.
     * The customerId argument must specify an absolute string.
     *
     * @param customerId
     * @return the CustomerDTO
     * @see CustomerInfo
     */
    public CustomerInfo findCustomerByCustomerId(String customerId) {
        log.info("findCustomerByCustomerId method called.");

        Optional<Customer> response = repository.findById(customerId);

        if (null != response) {
            return mapper.mapToCustomerDTO(response.stream().findAny().get());
        }
        return null;
    }

}
