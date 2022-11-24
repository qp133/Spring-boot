package com.qp.testjpa;

import com.github.javafaker.Faker;
import com.qp.testjpa.entity.Employee;
import com.qp.testjpa.entity.Product;
import com.qp.testjpa.repository.EmployeeRepository;
import com.qp.testjpa.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class TestJpaApplicationTests {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Faker faker;

    @Test
    void saveEmployee() {
        for (int i = 0; i <= 30; i++) {
            Employee employee = Employee.builder()
                    .emailAddress(faker.internet().emailAddress())
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .build();
            employeeRepository.save(employee);
        }
    }

    @Test
    void save_product() {
        for (int i = 0; i < 10; i++) {
            Product product = Product.builder().name(faker.commerce().productName()).build();
            productRepository.save(product);
        }
    }

    @Test
    void sortByFirstName() {
        List<Employee> employees = employeeRepository.findByFirstNameContains("a", Sort.by("firstName"));
        employees.forEach(System.out::println);
    }

    @Test
    void findByLastNameContains() {
        Page<Employee> pages = employeeRepository.findByLastNameContains("er", PageRequest.of(1,10));
        pages.getContent().forEach(System.out::println);
        System.out.println(pages.getTotalPages());
        System.out.println(pages.getTotalElements());
    }

}
