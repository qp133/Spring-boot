package vn.techmaster.carexercise.repository;

import org.springframework.stereotype.Repository;
import vn.techmaster.carexercise.entity.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Repository
public class CarRepository {
    private List<Car> cars = new ArrayList<>();

    public CarRepository() {
        List<Car> list = Arrays.asList(
                new Car(1, "Suburban 1500", "Chevrolet", "car_1"),
                new Car(2, "Cayenne", "Porsche", "car_2"),
                new Car(3, "9-7X", "Saab", "car_3")
        );
        list.forEach(c -> cars.add(c));
    }

    public List<Car> getAll() {
        return cars;
    }

    public Optional<Car> get(int id) {
        return cars.stream().filter(c -> c.getId()==id).findFirst();
    }

    public Car create(Car customer) {
        int id;
        if (cars.isEmpty()) {
            id = 1;
        } else {
            Car lastCustomer = cars.get(cars.size()-1);
            id = lastCustomer.getId() + 1;
        }
        customer.setId(id);
        cars.add(customer);
        return customer;
    }

    public Car edit(Car car) {
        get(car.getId()).ifPresent(existCar -> {
            existCar.setBrand(car.getBrand());
            existCar.setManufacturer(car.getManufacturer());
            existCar.setPhoto(car.getPhoto());
        });
        return car;
    }

    public void deleteById(int id) {
        get(id).ifPresent(exist -> cars.remove(exist));
    }

    public void delete(Car customer) {
        deleteById(customer.getId());
    }

    public Car search(String brand) {
        return cars.stream().filter(i -> i.getBrand().contains(brand)).findAny().orElse(null);
    }
}
