package vn.techmaster.personalmanagementcrud.repository;

import org.springframework.stereotype.Repository;
import vn.techmaster.personalmanagementcrud.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {
    private List<Person> persons = new ArrayList<>();

    public Optional<Person> get(int id) {
        return persons.stream().filter(c -> c.getId()==id).findFirst();
    }

    public List<Person> getAllPersons() {
        return this.persons;
    }

    public Person create(Person person) {
        int id;
        if (persons.isEmpty()) {
            id = 1;
        } else {
            Person lastPerson = persons.get(persons.size()-1);
            id = lastPerson.getId() + 1;
        }
        person.setId(id);
        persons.add(person);
        return person;
    }

    public Person edit(Person person) {
        get(person.getId()).ifPresent(existPerson -> {
            existPerson.setName(person.getName());
            existPerson.setJob(person.getJob());
            existPerson.setBirthdate(person.getBirthdate());
            existPerson.setPhoto(person.getPhoto());
        });
        return person;
    }

    public void deleteById(int id) {
        get(id).ifPresent(exist -> persons.remove(exist));
    }

    public void delete(Person person) {
        deleteById(person.getId());
    }

    public Person search(String name) {
        return persons.stream().filter(i -> i.getName().contains(name)).findAny().orElse(null);
    }
}
