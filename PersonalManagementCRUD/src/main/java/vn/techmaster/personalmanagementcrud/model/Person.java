package vn.techmaster.personalmanagementcrud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Person {
    private int id;
    @Size(min = 3, max = 30, message = "name size between 3 and 30")
    private String name;
    @NotBlank(message = "job not null")
    private String job;
    @NotBlank(message = "birthdate not null")
    private String birthdate;
    private MultipartFile photo;
}
