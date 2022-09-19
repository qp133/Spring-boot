package vn.techmaster.hw_customer_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vn.techmaster.hw_customer_crud.model.Customer;
import vn.techmaster.hw_customer_crud.repository.CustomerRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class CustomerController {
    private CustomerRepository customerRepo;

    public CustomerController() {
        customerRepo = new CustomerRepository();
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/listAll")
    public String listAll(Model model) {
        List<Customer> customers = customerRepo.getAllCustomer();
        model.addAttribute("people", customers);
        return "listAll";
    }

    @GetMapping("/listAll/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        Customer customer = customerRepo.get(id).get();
        model.addAttribute("customer", customer);
        return "detail";
    }

    @GetMapping("/create")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customerForm";
    }

    @GetMapping("/customer/edit/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model) {
        Optional<Customer> customer = customerRepo.get(id);
        if (customer.isPresent()) {
            model.addAttribute("customer", customer);
        }
        return "customerForm";
    }

    @PostMapping("/post")
    public String postInfo(@ModelAttribute("person") Customer customer, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            if (customer.getId() > 0) {
                customerRepo.edit(customer);
            } else {
                customerRepo.create(customer);
            }
            model.addAttribute("customer", customerRepo.getAllCustomer());
            return "redirect:/listAll";
        }
        return "customerForm";
    }

    @GetMapping("/customer/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        customerRepo.deleteById(id);
        model.addAttribute("people", customerRepo.getAllCustomer());
        return "redirect:/listAll";
    }

    @GetMapping("/search")
    public String searchCustomer(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        if (Objects.equals(email, "")) {
            model.addAttribute("people", customerRepo.getAllCustomer());
            return "redirect:/listAll";
        } else {
            Customer customer = customerRepo.search(email);
            model.addAttribute("people", customer);
            return "listAll";
        }
    }

    @GetMapping("/sort/{direction}")
    public String sortByFullName(@PathVariable("direction") String direction, Model model) {
        customerRepo.sortByFullName(direction);
        ArrayList<Customer> customers = (ArrayList<Customer>) customerRepo.getAllCustomer();
        model.addAttribute("people", customers);
        return "listAll";
    }
}
