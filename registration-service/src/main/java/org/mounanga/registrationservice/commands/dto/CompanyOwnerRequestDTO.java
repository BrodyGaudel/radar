package org.mounanga.registrationservice.commands.dto;

import jakarta.validation.constraints.NotBlank;

public record CompanyOwnerRequestDTO(

        @NotBlank(message = "field 'siret' is mandatory : it can not be blank")
        String siret,

        @NotBlank(message = "field 'name' is mandatory : it can not be blank")
        String name,

        @NotBlank(message = "field 'description' is mandatory : it can not be blank")
        String description,

        @NotBlank(message = "field 'address' is mandatory : it can not be blank")
        String address) {
}
