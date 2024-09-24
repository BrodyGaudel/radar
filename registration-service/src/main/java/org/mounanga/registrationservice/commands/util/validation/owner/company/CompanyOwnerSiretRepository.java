package org.mounanga.registrationservice.commands.util.validation.owner.company;

import org.springframework.data.repository.CrudRepository;

public interface CompanyOwnerSiretRepository extends CrudRepository<CompanyOwnerSiret, String> {

    boolean existsBySiret(String siret);
    CompanyOwnerSiret findBySiret(String siret);
}
