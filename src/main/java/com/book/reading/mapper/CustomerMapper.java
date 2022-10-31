package com.book.reading.mapper;

import com.book.reading.dto.customer.CustomerInfo;
import com.book.reading.model.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerInfo mapToCustomerDTO(Customer customer);

    List<CustomerInfo> mapToCustomerDTOList(List<Customer> customerList);

    Customer mapToCustomer(CustomerInfo customerInfo);
}
