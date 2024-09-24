package org.mounanga.registrationservice.commands.util.validation.owner.individual;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualOwnerNipRepository extends JpaRepository<IndividualOwnerNip, String> {
    boolean existsByNip(String nip);
    IndividualOwnerNip findByNip(String nip);
}
