package org.mounanga.registrationservice.queries.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetVehiclesByOwnerIdQuery {
    private String orderId;
    private int page;
    private int size;
}
