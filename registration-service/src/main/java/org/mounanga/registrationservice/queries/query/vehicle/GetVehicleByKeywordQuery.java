package org.mounanga.registrationservice.queries.query.vehicle;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetVehicleByKeywordQuery {
    private String keyword;
    private int page;
    private int pageSize;
}
