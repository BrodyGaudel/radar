package org.mounanga.registrationservice.queries.query.owner.company;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetCompanyOwnerBySiretQuery {
    private String siret;
}
