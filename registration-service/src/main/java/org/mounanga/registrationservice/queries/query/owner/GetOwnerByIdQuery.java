package org.mounanga.registrationservice.queries.query.owner;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetOwnerByIdQuery {
    private String ownerId;
}
