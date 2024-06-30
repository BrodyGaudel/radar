package org.mounanga.registrationservice.queries.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class SearchOwnerQuery {
    private String keyword;
    private int page;
    private int size;
}
