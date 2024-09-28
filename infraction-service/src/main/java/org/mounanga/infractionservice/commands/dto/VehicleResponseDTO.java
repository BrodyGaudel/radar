package org.mounanga.infractionservice.commands.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class VehicleResponseDTO {
    private String id;
    private String registrationId;
    private String marque;
    private String model;
    private Double taxPower;
    private String description;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;
    private String ownerId;
}
