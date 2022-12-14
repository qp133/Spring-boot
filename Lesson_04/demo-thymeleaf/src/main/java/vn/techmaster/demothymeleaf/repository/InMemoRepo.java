package vn.techmaster.demothymeleaf.repository;

import org.springframework.stereotype.Repository;
import vn.techmaster.demothymeleaf.model.Person;
import vn.techmaster.demothymeleaf.model.Product;
import vn.techmaster.demothymeleaf.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoRepo {
    private List<Product> products;
    private List<Person> people;
    private List<User> users;

    public InMemoRepo() {
        products = new ArrayList<>(List.of(new Product("Sony WX-MH1004", "Sony", 55000000),
                new Product("Nike Air", "Nike", 25000000),
                new Product("Dell M6800", "Dell", 105000000),
                new Product("Aris Pro", "VinSmart", 65000000),
                new Product("Logitech MX Master 2X", "Logitech", 32000000)));
        people = new ArrayList<>(List.of(
                new Person("Cường", "Vietnam", "1975-11-27", "male"),
                new Person("John", "USA", "1979-10-27", "male"),
                new Person("Lara", "Italy", "1999-10-27", "female"),
                new Person("Anna", "Russia", "1998-02-01", "female"),
                new Person("Liên", "Vietnam", "1992-02-01", "female"),
                new Person("Ziang", "China", "2001-05-06", "female"),
                new Person("Persie", "Holland", "1978-11-20", "male")));
        users = new ArrayList<>(List.of(
                new User("Cường", "admin"),
                new User("Huy", "editor"),
                new User("Lan", "author"),
                new User("Long", "user"),
                new User("Joe", "user")));
    }

    public List<Product> getProducts() {
        return products;
    }
    public List<Person> getPeople() {
        return people;
    }

    public List<User> getUsers() {
        return users;
    }
}
