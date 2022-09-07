package vn.techmaster.day02;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {
    private ArrayList<Car> car = new ArrayList<>();
    /* Nhiệm vụ của constructor này là đọc dữ liệu từ file JSON vào một ArrayList<Person>
     */
    public CarRepository() {
        try {
            File file = ResourceUtils.getFile("classpath:static/personsmall.json");
            ObjectMapper mapper = new ObjectMapper();
            car.addAll(mapper.readValue(file, new TypeReference<List<Car>>() {
            }));
            car.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Car> getAllCar() {
        return car;
    }
}
