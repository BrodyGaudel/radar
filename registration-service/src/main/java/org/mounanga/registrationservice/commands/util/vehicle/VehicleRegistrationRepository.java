package org.mounanga.registrationservice.commands.util.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRegistrationRepository extends JpaRepository<VehicleRegistration, String> {

    boolean existsByRegistrationId(String registrationId);
    VehicleRegistration findByRegistrationId(String registrationId);
}
