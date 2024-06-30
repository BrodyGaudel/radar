package org.mounanga.registrationservice.queries.query;

import lombok.*;

@AllArgsConstructor
@Getter
@ToString
public class GetAllOwnersQuery {
    private int page;
    private int size;
}
