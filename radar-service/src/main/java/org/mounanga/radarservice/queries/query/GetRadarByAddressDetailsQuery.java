package org.mounanga.radarservice.queries.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetRadarByAddressDetailsQuery {
    private Double longitude;
    private Double latitude;
}
