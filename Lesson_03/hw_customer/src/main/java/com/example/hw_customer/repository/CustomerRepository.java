package com.example.hw_customer.repository;

import com.example.hw_customer.mapper.CustomerMapper;
import com.example.hw_customer.model.Customer;
import com.example.hw_customer.model.CustomerPOJO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository {
    private List<Customer> customers = new ArrayList<>();

    public CustomerRepository() {
        customers.add(new Customer("Tom", "tom@gmail.com", 12345L));
        customers.add(new Customer("John", "john@gmail.com", 34567L));
        customers.add(new Customer("Mary", "mary@gmail.com", 48383L));
    }

    public List<Customer> getAllCustomer() {
        return customers;
    }

    public Customer findById(Integer id) {
        return customers.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    public Customer add(CustomerPOJO pojo) {
        Customer customer = CustomerMapper.INSTANCE.pojoToCustomer(pojo);
        customers.add(customer);
        return customer;
    }

    public Customer update(Integer id, CustomerPOJO pojo) {
        Customer updateCustomer = CustomerMapper.INSTANCE.pojoToCustomer(pojo);
        updateCustomer.setId(id);
        customers = customers.stream().map(c -> {
            if (c.getId() == id) {
                return updateCustomer;
            }
            else {
                return c;
            }
        }).collect(Collectors.toList());
        return updateCustomer;
    }

    public Customer delete(Integer id) {
        Optional<Customer> customer = customers.stream().filter(c -> c.getId() == id).findFirst();
        if (customer.isPresent()) {
            customers.remove(customer.get());
            return customer.get();
        }
        return null;
    }
}
