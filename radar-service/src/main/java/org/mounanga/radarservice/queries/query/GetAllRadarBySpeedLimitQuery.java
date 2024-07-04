package org.mounanga.radarservice.queries.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetAllRadarBySpeedLimitQuery {
    private Double speedLimitMax;
    private Double speedLimitMin;
    private String address;
    private int page;
    private int size;
}
