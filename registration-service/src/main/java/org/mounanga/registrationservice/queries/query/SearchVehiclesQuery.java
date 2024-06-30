package org.mounanga.registrationservice.queries.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SearchVehiclesQuery {
    private String keyword;
    private int page;
    private int size;
}
