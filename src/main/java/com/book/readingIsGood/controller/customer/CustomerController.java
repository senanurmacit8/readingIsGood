package com.book.readingIsGood.controller.customer;

import com.book.readingIsGood.dto.customer.CustomerDTO;
import com.book.readingIsGood.service.customer.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service){
        this.service= service;
    }

    @GetMapping("/listCustomers")
    public List<CustomerDTO> listCustomers(){
        List<CustomerDTO> customerDTOList = service.getAllCustomers();
        return customerDTOList;
    }

    @PostMapping("/createNewCustomer")
    @ResponseBody
    public ResponseEntity createNewCustomer(@RequestParam  CustomerDTO customerDTO){
        service.createNewCustomer(customerDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
