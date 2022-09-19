package vn.techmaster.hw_customer_crud.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private int id;
    private String fullname;
    private String email;
    private long number;

}
