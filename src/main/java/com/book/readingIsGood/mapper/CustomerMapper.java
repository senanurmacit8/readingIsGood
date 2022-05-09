package com.book.readingIsGood.mapper;

import com.book.readingIsGood.dto.customer.CustomerDTO;
import com.book.readingIsGood.model.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO mapToCustomerDTO(Customer customer);

    List<CustomerDTO> mapToCustomerDTOList(List<Customer> customerList);

    Customer mapToCustomer(CustomerDTO customerDTO);
}
