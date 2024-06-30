package org.mounanga.registrationservice.queries.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class GetOwnerByIdQuery {
    private  String id;
}
