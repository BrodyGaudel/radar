package org.mounanga.registrationservice.queries.repository;

import org.mounanga.registrationservice.queries.entity.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OwnerRepository extends JpaRepository<Owner, String> {

    @Query("select c from CompanyOwner c where c.name like :kw or c.description like :kw")
    Page<Owner> findCompanyOwnerByKeyword(@Param("kw") String keyword, Pageable pageable);

    @Query("select c from CompanyOwner c where c.siret = :siret")
    Owner findCompanyOwnerBySiret(@Param("siret") String siret);

    @Query("select i from IndividualOwner i where i.nip = :nip")
    Owner findIndividualOwnerByNip(@Param("nip") String nip);

    @Query("select i from IndividualOwner i where i.firstname like :kw or i.lastname like :kw or i.nip like :kw")
    Page<Owner>  findIndividualOwnerByKeyword(@Param("kw") String keyword, Pageable pageable);
}
