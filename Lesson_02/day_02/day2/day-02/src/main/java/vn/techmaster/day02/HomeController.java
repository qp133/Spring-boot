package vn.techmaster.day02;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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
}
