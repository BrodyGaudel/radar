package org.mounanga.registrationservice.queries.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class CompanyOwner extends Owner{
    private String siret;
    private String name;
    private String description;
    private String address;
}
