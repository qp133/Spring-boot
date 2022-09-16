package com.example.hw_customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPOJO {
    private String fullname;
    private String email;
    private long telephone;
}
