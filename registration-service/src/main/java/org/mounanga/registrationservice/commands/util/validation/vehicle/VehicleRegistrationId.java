package org.mounanga.registrationservice.commands.util.validation.vehicle;

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
public class VehicleRegistrationId {
    @Id
    private String vehicleId;

    @Column(unique=true, nullable=false)
    private String registrationId;
}
