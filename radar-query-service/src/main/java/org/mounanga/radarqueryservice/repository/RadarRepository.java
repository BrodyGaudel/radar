package org.mounanga.radarqueryservice.repository;


import org.mounanga.radarqueryservice.entity.Radar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RadarRepository extends JpaRepository<Radar, String> {

    @Query("select r from Radar r where r.address like :kw")
    Page<Radar> findByAddress(@Param("kw") String address, Pageable pageable);

    @Query("select r from Radar r where r.latitude = :lat and r.longitude = :lon")
    Page<Radar> findByLatitudeAndLongitude(@Param("lat") Double latitude, @Param("lon") Double longitude, Pageable pageable);

}
