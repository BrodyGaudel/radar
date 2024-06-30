package org.mounanga.registrationservice.commands.dto;

import lombok.*;
import org.mounanga.registrationservice.common.enums.Gender;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class OwnerRequestDTO {
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private Gender gender;
    private String nationality;
    private String cin;
}
