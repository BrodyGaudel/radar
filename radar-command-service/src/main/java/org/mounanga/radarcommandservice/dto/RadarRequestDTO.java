package org.mounanga.radarcommandservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RadarRequestDTO(
        @NotBlank(message = "field 'address' is mandatory : it can not be blank")
        String address,

        @NotNull(message = "field 'latitude' is mandatory : it can not be null")
        Double latitude,

        @NotNull(message = "field 'longitude' is mandatory : it can not be null")
        Double longitude,

        @NotNull(message = "field 'speedLimit' is mandatory : it can not be null")
        @Positive(message = "field 'speedLimit' must be a positive value")
        Double speedLimit) {
}