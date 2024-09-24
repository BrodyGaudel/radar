package org.mounanga.registrationservice.queries.dto;

import lombok.*;
import org.mounanga.registrationservice.common.enums.Gender;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class IndividualOwnerResponseDTO extends OwnerResponseDTO {
    private String nip;
    private String firstname;
    private String lastname;
    private String nationality;
    private LocalDate birthdate;
    private String placeOfBirth;
    private Gender gender;
}
