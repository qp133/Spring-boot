package vn.techmaster.personthymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {
    private int id;
    private String name;
    private String job;
    private boolean gender;
    private String Birthday;


}
