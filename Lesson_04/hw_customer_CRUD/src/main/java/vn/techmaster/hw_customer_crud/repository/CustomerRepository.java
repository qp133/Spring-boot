package vn.techmaster.hw_customer_crud.repository;

import org.springframework.stereotype.Repository;
import vn.techmaster.hw_customer_crud.model.Customer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {
    private ArrayList<Customer> customers = new ArrayList<>();

    public CustomerRepository() {
        List<Customer> list = List.of(
                new Customer(1,"Tom", "tom@gmail.com", 12345L),
                new Customer(2,"John", "john@gmail.com", 34567L),
                new Customer(3, "Mary", "mary@gmail.com", 48383L)
        );
        customers.addAll(list);
    }

    public List<Customer> getAllCustomer() {
        return customers;
    }

    public Optional<Customer> get(int id) {
        return customers.stream().filter(i -> i.getId() == id).findFirst();
    }

    public Customer create(Customer customer) {
        int id;
        if (customers.isEmpty()) {
            id = 1;
        } else {
            Customer lastCustomer = customers.get(customers.size()-1);
            id = lastCustomer.getId() + 1;
        }
        customer.setId(id);
        customers.add(customer);
        return customer;
    }

    public Customer edit(Customer customer) {
        get(customer.getId()).ifPresent(existPerson -> {
            existPerson.setFullname(customer.getFullname());
            existPerson.setEmail(customer.getEmail());
            existPerson.setNumber(customer.getNumber());
        });
        return customer;
    }

    public void deleteById(int id) {
        get(id).ifPresent(exist -> customers.remove(exist));
    }

    public void delete(Customer customer) {
        deleteById(customer.getId());
    }

    public Customer search(String email) {
        return customers.stream().filter(i -> i.getEmail().contains(email)).findAny().orElse(null);
    }

    public void sortByFullName(String direction) {
        customers.sort(new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (direction.equals("asc")) {
                    return o1.getFullname().compareTo(o2.getFullname());
                } else if (direction.equals("desc")) {
                    return o2.getFullname().compareTo(o1.getFullname());
                }
                return 0;
            }
        });
    }
}
