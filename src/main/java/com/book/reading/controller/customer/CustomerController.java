package com.book.reading.controller.customer;

import com.book.reading.dto.customer.CustomerInfo;
import com.book.reading.service.customer.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author senanurmacit
 * @version 1.1
 * @since 1.1
 */
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    /**
     * This method created for list customers
     *
     * @return
     */
    @GetMapping("/listCustomers")
    public List<CustomerInfo> listCustomers() {
        List<CustomerInfo> customerInfoList = service.getAllCustomers();
        return customerInfoList;
    }

    /**
     * This method created for create a new customer
     *
     * @param customerInfo
     * @return
     */
    @PostMapping("/createNewCustomer")
    public ResponseEntity<String> createNewCustomer(@Valid @RequestBody CustomerInfo customerInfo) {
        service.createNewCustomer(customerInfo);
        return ResponseEntity.ok("User is valid");
    }
}
