package vn.techmaster.personthymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.techmaster.personthymeleaf.repository.PersonRepository;

@Controller
public class PersonController {
    @Autowired
    private PersonRepository personRepo;

    @GetMapping("/people")
    public String getPeople(Model model){
        model.addAttribute("people", personRepo.getAllPerson());
        return "table";
    }
}
