package org.mounanga.registrationservice.commands.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.mounanga.registrationservice.common.enums.Gender;

import java.time.LocalDate;

public record IndividualOwnerRequestDTO(
        @NotBlank(message = "field 'nip' is mandatory : it can not be blank")
        String nip,

        @NotBlank(message = "field 'firstname' is mandatory : it can not be blank")
        String firstname,

        @NotBlank(message = "field 'lastname' is mandatory : it can not be blank")
        String lastname,

        @NotBlank(message = "field 'nationality' is mandatory : it can not be blank")
        String nationality,

        @NotNull(message = "field 'date of birth' is mandatory : it can not be null")
        @Past(message = "date of birth must be in past")
        LocalDate birthdate,

        @NotBlank(message = "field 'place of birth' is mandatory : it can not be blank")
        String placeOfBirth,

        @NotNull(message = "field 'gender' is mandatory : it can not be null")
        Gender gender
) {
}
