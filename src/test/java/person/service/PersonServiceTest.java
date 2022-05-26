package person.service;

import com.example._28stonehomework.models.Person;
import com.example._28stonehomework.repository.PersonRepository;
import com.example._28stonehomework.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
public class PersonServiceTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    private final Person person = new Person(1,
                "Test",
                "Person",
                "123456",
                "test@email.com",
                LocalDate.of(2000, 10, 26));


    @DisplayName("JUnit test for addPerson() method")
    @Test
    public void addPersonIsSuccessful(){

        given(personRepository.save(person)).willReturn(person);

        Person savedPerson = personService.addPerson(person);

        assertThat(savedPerson).isNotNull();
        assertThat(savedPerson).isEqualTo(person);
    }

    //Not working yet
    @DisplayName("JUnit test to check that you cannot add identical person")
    @Test
    public void addingIdenticalPersonIsNotSuccessful(){

        Exception exception = null;

        Person identicalPerson = new Person(1,
                "Test",
                "Person",
                "123456",
                "test@email.com",
                LocalDate.of(2022, 10, 26));


        willThrow(new ResponseStatusException(HttpStatus.CONFLICT)).given(personRepository).save(identicalPerson);

        try{
            personService.addPerson(identicalPerson);
        } catch (ResponseStatusException e) {
            exception = e;
        }

        Assertions.assertNotNull(exception);
    }

    //Not working yet
    @DisplayName("JUnit test for getPersonsWithGivenAgeInterval() method")
    @Test

    public void willReturnPersonWhenPassedIntervalCorresponds(){

        List<Person> persons = new ArrayList<>();
        persons.add(person);
        System.out.println(persons);

//        doReturn(persons).when(personRepository.findSpecificAgePersons(LocalDate.of(1995, 5, 26), LocalDate.of(2005, 5, 26)));
//        given(personRepository.findSpecificAgePersons(LocalDate.of(1995, 5, 26), LocalDate.of(2005, 5, 26)))
//                .willReturn();

        Mockito.lenient().when(personRepository
                .findSpecificAgePersons(LocalDate.of(1995, 10, 26), LocalDate.of(2005, 10, 26))).
                thenReturn(persons);

        List<Person> matchingPersons = personService.getPersonsWithGivenAgeInterval(20, 30);
        System.out.println(matchingPersons);

        assertThat(matchingPersons.get(0)).isEqualTo(person);
    }

    @DisplayName("JUnit test for getAllPersons() method")
    @Test
    public void addingAndReturningMultiplePersonsIsSuccessful(){

        Person anotherPerson = new Person(2,
                "TestTwo",
                "PersonTwo",
                "123456",
                "test@email.com",
                LocalDate.of(2022, 5, 26));

        given(personRepository.findAll()).willReturn(List.of(person, anotherPerson));

        List<Person> personList = personService.getAllPersons();

        assertThat(personList).isNotNull();
        assertThat(personList.size()).isEqualTo(2);

    }

    @DisplayName("JUnit test for addNewPersons() method")
    @Test
    public void addingNewPersonsListErasesExistingOnes(){

        List<Person> newPersons = List.of( new Person(1,
                "TestTwo",
                "PersonTwo",
                "123456",
                "test@email.com",
                LocalDate.of(2022, 5, 26)),

                new Person(2,
                "TestThree",
                "PersonThree",
                "123456",
                "test@email.com",
                LocalDate.of(2022, 5, 26)));

        Person personFromList = newPersons.get(1);

        given(personRepository.saveAll(newPersons)).willReturn(newPersons);

        List<Person> personList = personService.addNewPersons(newPersons);

        assertThat(personList.size()).isEqualTo(2);
        assertThat(personList.get(1)).isEqualTo(personFromList);

    }
}
