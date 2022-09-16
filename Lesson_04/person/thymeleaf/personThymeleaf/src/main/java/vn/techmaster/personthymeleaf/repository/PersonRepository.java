package vn.techmaster.personthymeleaf.repository;

import org.springframework.stereotype.Repository;
import vn.techmaster.personthymeleaf.model.Person;


import java.util.List;

@Repository
public class PersonRepository {
    private List<Person> person;

    public PersonRepository() {
        person = List.of(
                new Person(1, "Quang", "Developer", true, "2001-03-13"),
            new Person(2, "Hoa", "Tester", false, "1998-08-16"),
            new Person(3, "Nam", "Banker", true, "1988-12-13"),
            new Person(4, "Trung", "Taxi Driver", true, "1995-09-31")
        );

    }

    public List<Person> getAllPerson() {
        return person;
    }
}
