package org.mounanga.registrationservice.commands.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class VehicleRequestDTO {
    private String registrationId;
    private String marque;
    private String model;
    private String description;
    private Double taxPower;
    private String ownerId;
}
