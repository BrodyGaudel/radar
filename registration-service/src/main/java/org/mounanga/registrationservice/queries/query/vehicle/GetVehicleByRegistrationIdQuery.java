package org.mounanga.registrationservice.queries.query.vehicle;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetVehicleByRegistrationIdQuery {
    private String registrationId;
}
