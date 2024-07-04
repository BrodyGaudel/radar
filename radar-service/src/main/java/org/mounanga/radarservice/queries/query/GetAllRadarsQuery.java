package org.mounanga.radarservice.queries.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetAllRadarsQuery {
    private int page;
    private int pageSize;
}
