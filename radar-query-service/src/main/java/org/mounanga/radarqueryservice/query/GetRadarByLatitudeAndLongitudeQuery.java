package org.mounanga.radarqueryservice.query;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetRadarByLatitudeAndLongitudeQuery {
    private Double latitude;
    private Double longitude;
    private int page;
    private int size;
}
