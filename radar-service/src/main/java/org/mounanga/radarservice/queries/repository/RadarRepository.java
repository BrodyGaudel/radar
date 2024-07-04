package org.mounanga.radarservice.queries.repository;

import org.mounanga.radarservice.queries.entity.Radar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RadarRepository extends JpaRepository<Radar, String> {

    @Query("select r from Radar r where r.address like :address")
    Page<Radar> findByAddress(@Param("address") String address, Pageable pageable);

    @Query("select r from Radar r where r.latitude =:latitude and r.longitude =:longitude")
    Optional<Radar> findByLatitudeAndLongitude(@Param("latitude") Double latitude,@Param("longitude") Double longitude);
}
