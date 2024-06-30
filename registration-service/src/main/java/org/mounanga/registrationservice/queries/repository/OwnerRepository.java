package org.mounanga.registrationservice.queries.repository;

import org.mounanga.registrationservice.queries.entity.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, String> {

    @Query("select o from Owner o where o.cin like :kw or o.lastname  like :kw or o.firstname like :kw")
    Page<Owner> search(@Param("kw") String keyword, Pageable pageable);


    Optional<Owner> findByCin(String cin);

}
