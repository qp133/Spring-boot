package vn.techmaster.personalmanagementcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vn.techmaster.personalmanagementcrud.model.Person;
import vn.techmaster.personalmanagementcrud.repository.JobRepository;
import vn.techmaster.personalmanagementcrud.repository.PersonRepository;
import vn.techmaster.personalmanagementcrud.service.StorageService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Controller
public class PersonController {
    @Autowired
    private PersonRepository personRepo;
    @Autowired
    private JobRepository jobRepo;
    @Autowired
    private StorageService storageService;

    @Autowired private MessageSource messageSource;


    public PersonController() {
        personRepo = new PersonRepository();
    }

    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("person", new Person());
        model.addAttribute("jobs", jobRepo.getAllJobs());
        return "index";
    }

    @GetMapping("/listAllPersons")
    public String listAll(Model model) {
        List<Person> persons = personRepo.getAllPersons();
        model.addAttribute("persons", persons);
        return "listAll";
    }

    @GetMapping("/person/{id}")
    public String personInfo(@PathVariable("id")int id, Model model){
        Optional<Person> person = personRepo.get(id);
        if(person.isPresent()){
            model.addAttribute("person",person.get());
            return "personInfo";
        }
        return "home";
    }

    @GetMapping("/create")
    public String showForm(Model model) {
        model.addAttribute("person", new Person());
        return "personForm";
    }

    @GetMapping("/person/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        Optional<Person> person = personRepo.get(id);
        if (person.isPresent()) {
            model.addAttribute("person", person);
        }
        return "personForm";
    }

    @PostMapping(value = "/post", consumes = {"multipart/form-data"})
    public String postInfo(@Valid @ModelAttribute("person") Person person, BindingResult result, Model model) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        if (person.getPhoto().getOriginalFilename().isEmpty()) {
            result.addError(new FieldError("person", "photo",
                    messageSource.getMessage("photo.required", null, "Photo required", locale)));
        }
        if (result.hasErrors()) {
            model.addAttribute("jobs",jobRepo.getAllJobs());
            return "index";
        }

        if (person.getId() > 0) {
            personRepo.edit(person);
        } else {
            personRepo.create(person);
        }
        storageService.uploadFile(person.getPhoto(), person.getId());
        model.addAttribute("persons", personRepo.getAllPersons());
        return "redirect:/listAllPersons";
    }

    @GetMapping("/person/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        storageService.deleteFile(id);
        personRepo.deleteById(id);
        model.addAttribute("person", personRepo.getAllPersons());
        return "redirect:/listAllPersons";
    }
}
