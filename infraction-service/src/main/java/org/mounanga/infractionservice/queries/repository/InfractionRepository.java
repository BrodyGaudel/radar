package org.mounanga.infractionservice.queries.repository;

import org.mounanga.infractionservice.queries.entity.Infraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface InfractionRepository extends JpaRepository<Infraction, String> {

    @Query("select i from Infraction i where i.radarId =:id and i.dateTime >= :start and i.dateTime <= :end")
    Page<Infraction> findByRadarId(@Param("id") String radarId,
                                   @Param("start")  LocalDateTime start,
                                   @Param("end")  LocalDateTime end,
                                   Pageable pageable);

    @Query("select i from Infraction i where i.vehicleId =:id and i.dateTime >= :start and i.dateTime <= :end")
    Page<Infraction> findByVehicleId(@Param("id") String vehicleId,
                                   @Param("start")  LocalDateTime start,
                                   @Param("end")  LocalDateTime end,
                                   Pageable pageable);
}
