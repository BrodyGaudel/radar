package org.mounanga.registrationservice.commands.util.vehicle;

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
public class VehicleRegistration {

    @Id
    private String vehicleId;

    @Column(nullable = false, unique = true)
    private String registrationId;
}
