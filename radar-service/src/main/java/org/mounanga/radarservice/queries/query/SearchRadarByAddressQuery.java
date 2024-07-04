package org.mounanga.radarservice.queries.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SearchRadarByAddressQuery {
    private String address;
    private int page;
    private int size;
}
