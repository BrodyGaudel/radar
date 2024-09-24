package org.mounanga.registrationservice.queries.query.owner.company;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetCompanyOwnerByKeywordQuery {
    private String keyword;
    private int page;
    private int size;
}
