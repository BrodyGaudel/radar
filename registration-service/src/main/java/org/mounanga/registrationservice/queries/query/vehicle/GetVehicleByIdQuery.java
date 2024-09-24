package org.mounanga.registrationservice.queries.query.vehicle;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetVehicleByIdQuery {
    private String vehicleId;
}
