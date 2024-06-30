package org.mounanga.registrationservice.common.event.owner;

import lombok.Getter;
import org.mounanga.registrationservice.common.enums.Gender;
import org.mounanga.registrationservice.common.event.BaseEvent;

import java.time.LocalDate;

@Getter
public class OwnerCreatedEvent extends BaseEvent<String> {

    private final String firstname;
    private final String lastname;
    private final LocalDate dateOfBirth;
    private final String placeOfBirth;
    private final Gender gender;
    private final String nationality;
    private final String cin;

    public OwnerCreatedEvent(String id, String firstname, String lastname, LocalDate dateOfBirth, String placeOfBirth, Gender gender, String nationality, String cin) {
        super(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.cin = cin;
    }
}
