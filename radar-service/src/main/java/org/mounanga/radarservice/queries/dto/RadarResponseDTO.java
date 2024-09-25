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
    private String address;
    private Double latitude;
    private Double longitude;
    private Double speedLimit;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private String createBy;
    private String lastModifiedBy;
}
