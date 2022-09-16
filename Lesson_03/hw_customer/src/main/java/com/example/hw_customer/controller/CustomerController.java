package com.example.hw_customer.controller;

import com.example.hw_customer.model.CustomerPOJO;
import com.example.hw_customer.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private CustomerRepository customerRepo;

    public CustomerController(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomer() {
        return ResponseEntity.status(HttpStatus.OK).body(customerRepo.getAllCustomer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerRepo.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody CustomerPOJO pojo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerRepo.add(pojo));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Integer id, @RequestBody CustomerPOJO pojo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerRepo.update(id, pojo));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerRepo.delete(id));
    }
}
