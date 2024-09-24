package org.mounanga.registrationservice.queries.query.vehicle;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetVehicleByOwnerIdQuery {
    private String ownerId;
    private int page;
    private int pageSize;
}
