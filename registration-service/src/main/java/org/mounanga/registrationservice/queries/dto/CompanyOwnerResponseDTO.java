package org.mounanga.registrationservice.queries.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CompanyOwnerResponseDTO extends OwnerResponseDTO {
    private String siret;
    private String name;
    private String description;
    private String address;
}
