package vn.techmaster.carexercise.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private int id;

    @NotBlank(message = "Brand cannot null")
    @Email(message = "Not valid brand")
    private String brand;

    @NotBlank(message = "Manufacturer cannot null")
    @Email(message = "Not valid manufacturer")
    private String manufacturer;
    private String photo;
}
