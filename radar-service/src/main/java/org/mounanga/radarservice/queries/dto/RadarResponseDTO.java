package org.mounanga.radarservice.queries.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RadarResponseDTO {
    private String id;
    private Double speedLimit;
    private String address;
    private Double longitude;
    private Double latitude;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
