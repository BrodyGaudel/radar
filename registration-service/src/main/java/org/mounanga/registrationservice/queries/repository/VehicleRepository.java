package org.mounanga.registrationservice.queries.repository;

import org.mounanga.registrationservice.queries.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    @Query("select v from Vehicle v where v.registrationId = :rid")
    Optional<Vehicle> findByRegistrationId(@Param("rid") String registrationId);

    @Query("select v from Vehicle v where v.owner.id = :ownerId")
    Page<Vehicle> findByOwnerId(@Param("ownerId") String ownerId, Pageable pageable);

    @Query("select v from Vehicle v where v.registrationId like :kw or v.model like :kw or v.marque like :kw")
    Page<Vehicle> search(@Param("kw") String keyword, Pageable pageable);
}
