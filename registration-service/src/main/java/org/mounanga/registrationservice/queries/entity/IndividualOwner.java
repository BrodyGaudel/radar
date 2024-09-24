package org.mounanga.registrationservice.queries.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mounanga.registrationservice.common.enums.Gender;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class IndividualOwner extends Owner{

    private String nip;
    private String firstname;
    private String lastname;
    private String nationality;
    private LocalDate birthdate;
    private String placeOfBirth;
    private Gender gender;
}
