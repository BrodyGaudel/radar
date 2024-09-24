package org.mounanga.registrationservice.commands.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record VehicleRequestDTO(
        @NotBlank(message = "field 'vehicleId' is mandatory: it cannot be blank")
        String vehicleId,

        @NotBlank(message = "field 'registrationId' is mandatory: it cannot be blank")
        String registrationId,

        @NotBlank(message = "field 'marque' is mandatory: it cannot be blank")
        String marque,

        @NotBlank(message = "field 'model' is mandatory: it cannot be blank")
        String model,

        @NotNull(message = "field 'taxPower' is mandatory: it cannot be null")
        @Positive(message = "field 'taxPower' must be a positive value")
        Double taxPower,

        @NotBlank(message = "field 'description' is mandatory: it cannot be blank")
        String description,

        @NotBlank(message = "field 'ownerId' is mandatory: it cannot be blank")
        String ownerId) {
}
