package com.example.hw_customer.mapper;

import com.example.hw_customer.model.Customer;
import com.example.hw_customer.model.CustomerPOJO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerPOJO customerToPojo(Customer customer);
    Customer pojoToCustomer(CustomerPOJO customerPOJO);
}
