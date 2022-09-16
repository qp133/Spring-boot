package com.example.hw_customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Customer {
    private static int count = 0;
    private int id;
    private String fullname;
    private String email;
    private long telephone;

    public Customer() {
        this.id = ++count;
    }

    public Customer(String fullname, String email, long telephone) {
        this.fullname = fullname;
        this.email = email;
        this.telephone = telephone;
        this.id = ++count;
    }
}
