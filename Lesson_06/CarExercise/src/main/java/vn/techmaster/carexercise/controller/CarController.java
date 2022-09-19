package vn.techmaster.carexercise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vn.techmaster.carexercise.entity.Car;
import vn.techmaster.carexercise.repository.CarRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class CarController {
    private CarRepository carRepo;

    public CarController() {
        carRepo = new CarRepository();
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/listAll")
    public String listAll(Model model) {
        List<Car> cars = carRepo.getAll();
        model.addAttribute("cars", cars);
        return "listAll";
    }

    @GetMapping("/listAll/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        Car car = carRepo.get(id).get();
        model.addAttribute("car", car);
        return "detail";
    }

    @GetMapping("/create")
    public String showForm(Model model) {
        model.addAttribute("car", new Car());
        return "carForm";
    }

    @GetMapping("/customer/edit/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model) {
        Optional<Car> car = carRepo.get(id);
        if (car.isPresent()) {
            model.addAttribute("car", car);
        }
        return "carForm";
    }

    @PostMapping("/post")
    public String postInfo(@ModelAttribute("car") Car car, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            if (car.getId() > 0) {
                carRepo.edit(car);
            } else {
                carRepo.create(car);
            }
            model.addAttribute("customer", carRepo.getAll());
            return "redirect:/listAll";
        }
        return "carForm";
    }

    @GetMapping("/customer/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        carRepo.deleteById(id);
        model.addAttribute("car", carRepo.getAll());
        return "redirect:/listAll";
    }

    @GetMapping("/search")
    public String searchCar(HttpServletRequest request, Model model) {
        String brand = request.getParameter("brand");
        if (Objects.equals(brand, "")) {
            model.addAttribute("cars", carRepo.getAll());
            return "redirect:/listAll";
        } else {
            Car car = carRepo.search(brand);
            model.addAttribute("cars", car);
            return "listAll";
        }
    }
}
