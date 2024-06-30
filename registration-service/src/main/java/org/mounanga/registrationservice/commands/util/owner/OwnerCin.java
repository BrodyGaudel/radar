package org.mounanga.registrationservice.commands.util.owner;

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
public class OwnerCin {
    @Id
    private String ownerId;

    @Column(nullable = false, unique = true)
    private String cin;
}
