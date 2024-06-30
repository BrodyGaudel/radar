package org.mounanga.registrationservice.queries.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetVehicleByRegistrationIdQuery {
    private String registerId;
}
