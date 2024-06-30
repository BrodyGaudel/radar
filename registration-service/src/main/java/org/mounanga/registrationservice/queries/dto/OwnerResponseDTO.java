package org.mounanga.registrationservice.queries.dto;

import lombok.*;
import org.mounanga.registrationservice.common.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class OwnerResponseDTO {

    private String id;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private Gender gender;
    private String nationality;
    private String cin;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;
}
