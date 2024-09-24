package org.mounanga.registrationservice.queries.query.owner.individual;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetIndividualOwnerByKeywordQuery {
    private String keyword;
    private int page;
    private int size;
}
