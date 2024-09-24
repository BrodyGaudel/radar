package org.mounanga.registrationservice.commands.util.validation.owner.company;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CompanyOwnerSiret {
    @Id
    private String ownerId;

    @Column(unique = true, nullable = false)
    private String siret;
}
