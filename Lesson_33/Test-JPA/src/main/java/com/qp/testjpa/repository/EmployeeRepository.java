package com.qp.testjpa.repository;

import com.qp.testjpa.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByEmailAddressAndLastName(String emailAddress, String lastName);
    Set<Employee> findByFirstNameOrLastName(String firstName, String lastName);
    List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);
    List<Employee> findByFirstNameIgnoreCase(String firstName);

    @Query("select e from Employee e where e.emailAddress=?1")
    List<Employee> findAllByEmailAddress(String emailAddress);

    List<Employee> fetchByFirstName(String firstName);


    List<Employee> findByFirstNameContains(String firstName, Sort sort);
    Page<Employee> findByLastNameContains(String lastName, Pageable pageable);
}