package org.mounanga.registrationservice.queries.dto;

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
    private String description;
    private Double taxPower;
    private String ownerId;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private String createBy;
    private String lastModifiedBy;
}
