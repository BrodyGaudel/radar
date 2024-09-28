package org.mounanga.infractionservice.queries.query;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetInfractionByVehicleIdQuery {
    private String vehicleId;
    private LocalDateTime start;
    private LocalDateTime end;
    private int page;
    private int size;
}
