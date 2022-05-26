package com.example._28stonehomework.cotroller;

import com.example._28stonehomework.models.Person;
import com.example._28stonehomework.service.PersonService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @ApiOperation(value = "View a list of persons in database")
    @GetMapping("persons")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @ApiOperation(value = "Add a new person to database", response = Person.class)
    @PutMapping(value = "add")
    @ResponseStatus(HttpStatus.CREATED)
    public Person addPerson(@RequestBody @Valid Person person) {
        return personService.addPerson(person);
    }

    @ApiOperation(value = "Return persons with an age between specified query parameters", response = Person.class)
    @GetMapping(value = "personsByAge", params = {"fromAge", "toAge"})
    public List<Person> getPersonsWithGivenAgeInterval(@RequestParam Integer fromAge, @RequestParam Integer toAge) {
        return personService.getPersonsWithGivenAgeInterval(fromAge, toAge);
    }

    @ApiOperation(value = "Replacing existing persons in database with a list of new persons")
    @PutMapping(value = "newPersons")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Person> addNewPersons(@RequestBody List<Person> persons) {
        return personService.addNewPersons(persons);
    }

}
