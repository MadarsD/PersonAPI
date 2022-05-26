package com.example._28stonehomework.repository;


import com.example._28stonehomework.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByNameAndSurnameAndPhoneAndEmailAndBirthDate(String name, String surname, String phone, String email, LocalDate birthDate);

    @Query("SELECT p FROM Person p WHERE  p.birthDate >= :birthDateFrom and p.birthDate <=:birthDateTo")
    List<Person> findSpecificAgePersons(@Param("birthDateFrom") LocalDate birthDateFrom,
                                        @Param("birthDateTo") LocalDate birthDateTo);

}
