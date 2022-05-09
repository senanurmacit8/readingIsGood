package com.book.readingIsGood.controller.customer;

import com.book.readingIsGood.dto.customer.CustomerDTO;
import com.book.readingIsGood.service.customer.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service){
        this.service= service;
    }

    @GetMapping("/AllCustomers")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> customerDTOList = service.getAllCustomers();
        return customerDTOList;
    }

    @PostMapping("/createNewCustomer")
    @ResponseBody
    public String createNewCustomer(@RequestParam  CustomerDTO customerDTO){
        service.createNewCustomer(customerDTO);
        return "success";
    }

    @GetMapping("/customerOrders")
    public List<String> getCustomerOrders(@RequestParam String customerId){

     List<String> responseList = service.getCustomerOrders(customerId);

     return responseList;
    }

}
