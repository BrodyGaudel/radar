package org.mounanga.radarqueryservice.query;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetRadarByAddressQuery {
    private String address;
    private int page;
    private int size;
}
