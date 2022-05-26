package com.example._28stonehomework.service;

import com.example._28stonehomework.models.Person;
import com.example._28stonehomework.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Person addPerson(Person person) {
        Optional<Person> existingPerson = personRepository
                .findByNameAndSurnameAndPhoneAndEmailAndBirthDate(
                        person.getName(),
                        person.getSurname(),
                        person.getPhone(),
                        person.getEmail(),
                        person.getBirthDate());

        if (existingPerson.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return personRepository.save(person);
    }

    public List<Person> getPersonsWithGivenAgeInterval(Integer fromAge, Integer toAge) {
        LocalDate now = LocalDate.now();
        LocalDate birthDateFrom = now.minusYears(toAge);
        LocalDate birthDateTo = now.minusYears(fromAge);

        return personRepository.findSpecificAgePersons(birthDateFrom, birthDateTo);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public List<Person> addNewPersons(List<Person> persons) {
        deleteAllPersons();
        return personRepository.saveAll(persons);
    }

    public void deleteAllPersons() {
        personRepository.deleteAll();
    }
}
