package org.mounanga.registrationservice.commands.util.validation.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleRegistrationIdRepository extends JpaRepository<VehicleRegistrationId,String> {

    boolean existsByRegistrationId(String registrationId);
    VehicleRegistrationId findByRegistrationId(String registrationId);

    @Query("select v from VehicleRegistrationId v where v.vehicleId = :vid and v.registrationId = :rid")
    VehicleRegistrationId findByVehicleIdAndRegistrationId(@Param("vid") String vehicleId, @Param("rid") String registrationId);
}
