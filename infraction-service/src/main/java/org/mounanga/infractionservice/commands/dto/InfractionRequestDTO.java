package org.mounanga.infractionservice.commands.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record InfractionRequestDTO(
        @NotNull(message = "field 'dateTime' is mandatory: it can not be null")
        LocalDateTime dateTime,

        @NotNull(message = "field 'speed' is mandatory: it can not be null")
        @Positive(message = "field 'speed' must have positive value")
        Double speed,

        @NotNull(message = "field 'amount' is mandatory: it can not be null")
        @Positive(message = "field 'amount' must have positive value")
        Double amount,

        @NotBlank(message = "field 'vehicleId' is mandatory: it can not be blank")
        String vehicleId,

        @NotBlank(message = "field 'radarId' is mandatory: it can not be blank")
        String radarId) {
}
