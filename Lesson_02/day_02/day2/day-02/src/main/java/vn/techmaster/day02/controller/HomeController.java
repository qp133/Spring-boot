package vn.techmaster.day02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.day02.Animal;
import vn.techmaster.day02.model.Car;
import vn.techmaster.day02.repository.CarRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping
public class HomeController {
    //HTML,CSS:     GET, POST, PUT, DELETE, PATCH

    @GetMapping("/home")
    //có thể đặt @GetMapping("/home") và xóa bỏ @RequestMapping("/home") ở trên
    public String home() {
        return "Home";
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Animal getJSON() {
        return new Animal("mammal", "dog");
    }

    @GetMapping(value = "/jsonArrayList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Animal> getJSONArrayList() {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Animal("mammal", "dog"));
        animals.add(new Animal("mammal", "cat"));
        return animals;
    }

    @Autowired
    private CarRepository carRepo;
    @GetMapping("/car")
    public ResponseEntity<List<Car>> getAllCar() {
        return ResponseEntity.ok().body(carRepo.getAllCar());
    }

    @GetMapping("/carSort")
    public ArrayList<Car> carSort() {
        CarRepository carRepository = new CarRepository();
        ArrayList<Car> carArrayList = new ArrayList<>();
        carArrayList = (ArrayList<Car>) carRepository.getAllCar();
        Collections.sort(carArrayList, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getBrand().compareTo(o2.getBrand());
            }
        });
        return carArrayList;
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Animal> getXML() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("mammal", "dog"));
        animals.add(new Animal("mammal", "cat"));
        return animals;
    }

    @GetMapping("/add/{a}/{b}")
    @ResponseBody
    public int add(@PathVariable("a") int a, @PathVariable("b") int b) {
        return a + b;
    }
    @GetMapping("/name/{your_name}")
    @ResponseBody
    public String hi(@PathVariable("your_name") String yourName) {
        return "Hi " + yourName;
    }

    @GetMapping("/add")
    @ResponseBody
    public int add2(@RequestParam("a") int a, @RequestParam("b") int b) {
        return a + b;
    }
}
