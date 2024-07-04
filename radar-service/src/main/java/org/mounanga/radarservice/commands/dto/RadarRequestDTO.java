package org.mounanga.radarservice.commands.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RadarRequestDTO(
        @NotNull(message = "speedLimit is mandatory : it can not be null")
        Double speedLimit,
        @NotBlank(message = "address is mandatory : it can not be blank")
        String address,
        @NotNull(message = "longitude is mandatory : it can not be null")
        Double longitude,
        @NotNull(message = "latitude is mandatory : it can not be null")
        Double latitude) {
}
