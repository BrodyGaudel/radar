package org.mounanga.infractionservice.queries.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class InfractionResponseDTO {
    private String id;
    private LocalDateTime dateTime;
    private Double speed;
    private Double amount;
    private String vehicleId;
    private String radarId;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;
}
