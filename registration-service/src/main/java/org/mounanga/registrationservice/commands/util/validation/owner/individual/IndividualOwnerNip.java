package org.mounanga.registrationservice.commands.util.validation.owner.individual;

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
public class IndividualOwnerNip {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String nip;
}
