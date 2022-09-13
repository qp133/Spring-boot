package vn.techmaster.hw_ontapdulieu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.hw_ontapdulieu.model.Person;
import vn.techmaster.hw_ontapdulieu.repository.PersonRepository;
import vn.techmaster.hw_ontapdulieu.service.PersonService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PersonController {
    ArrayList<Person> allPerson = new ArrayList<>();
    PersonRepository personRepo;
    PersonService personService = new PersonService();
    HashMap<String, Long> hashMap = new HashMap<>();

    @Autowired
    public PersonController (ArrayList<Person> allPerson, PersonRepository personRepo) {
        this.allPerson = allPerson;
        this.personRepo = personRepo;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Person> getAllPerson() {
        allPerson = personRepo.getAllPerson();
        return allPerson;
    }

    @GetMapping("/people")
    public ArrayList<Person> sortListPerson(@RequestParam String sort, @RequestParam String direction) {
        if (sort.equals("name")) {
            personService.sortByName(allPerson, direction);
        }
        if (sort.equals("nationality")){
            personService.sortByNationality(allPerson,direction);
        }  if (sort.equals("age")){
            personService.sortByAge(allPerson,direction);
        }
        return allPerson;
    }

    @GetMapping("/nationality")
    public HashMap<String, Long> getAllNationality() {
        hashMap = personRepo.getAllNationality(allPerson);
        return hashMap;
    }

    @GetMapping("/sortNationalityByList")
    public List<Map.Entry<String, Long>> listNationality(@RequestParam String direction) {
        return personService.sortNationalityByList(hashMap,direction);
    }

    @GetMapping("/sortNationalityByMap")
    public HashMap<String, Long> mapNationality(@RequestParam String direction) {
        return personService.sortNationalityByMap(hashMap, direction);
    }
}
