package com.example._28stonehomework.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "persons", uniqueConstraints = {@UniqueConstraint(name = "UniquePerson",
columnNames = {"name", "surname", "phone", "email", "birth_date"}) })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "person_id")
    private int personId;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String phone;
    @NotNull
    private String email;
    @NotNull
    @Column(name = "birth_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;


}
