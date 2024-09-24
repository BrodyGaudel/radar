package org.mounanga.registrationservice.queries.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.mounanga.registrationservice.queries.dto.OwnerResponseDTO;
import org.mounanga.registrationservice.queries.entity.Owner;
import org.mounanga.registrationservice.queries.query.owner.GetOwnerByIdQuery;
import org.mounanga.registrationservice.queries.query.owner.company.GetCompanyOwnerByKeywordQuery;
import org.mounanga.registrationservice.queries.query.owner.company.GetCompanyOwnerBySiretQuery;
import org.mounanga.registrationservice.queries.query.owner.individual.GetIndividualOwnerByKeywordQuery;
import org.mounanga.registrationservice.queries.query.owner.individual.GetIndividualOwnerByNipQuery;
import org.mounanga.registrationservice.queries.repository.OwnerRepository;
import org.mounanga.registrationservice.queries.util.DtoFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OwnerQueryHandlerService {

    private final OwnerRepository ownerRepository;

    public OwnerQueryHandlerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }


    @QueryHandler
    public OwnerResponseDTO handle(GetOwnerByIdQuery query) {
        log.info("Handling GetOwnerByIdQuery: {}", query);
        Owner owner = ownerRepository.findById(query.getOwnerId()).orElse(null);
        if (owner == null) {
            log.warn("Owner not found with id: {} not found", query.getOwnerId());
            return null;
        }
        log.info("owner with id: {} found", owner);
        return DtoFactory.fromOwner(owner);
    }

    @QueryHandler
    public OwnerResponseDTO handle(GetCompanyOwnerBySiretQuery query){
        log.info("Handling GetCompanyOwnerBySiretQuery: {}", query);
        Owner owner = ownerRepository.findCompanyOwnerBySiret(query.getSiret());
        if (owner == null) {
            log.warn("Company owner not found with siret: {} not found", query.getSiret());
            return null;
        }
        log.info("company owner with siret: {} found", owner);
        return DtoFactory.fromOwner(owner);
    }

    @QueryHandler
    public List<OwnerResponseDTO> handle(GetCompanyOwnerByKeywordQuery query){
        log.info("Handling GetCompanyOwnerByKeywordQuery: {}", query);
        String keyword = "%"+query.getKeyword()+"%";
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        List<Owner> owners = ownerRepository.findCompanyOwnerByKeyword(keyword, pageable).getContent();
        log.info("company owner with keyword: {} found", keyword);
        return DtoFactory.fromListOfOwners(owners);
    }

    @QueryHandler
    public OwnerResponseDTO handle(GetIndividualOwnerByNipQuery query){
        log.info("Handling GetIndividualOwnerByNipQuery: {}", query);
        Owner owner = ownerRepository.findIndividualOwnerByNip(query.getNip());
        if (owner == null) {
            log.warn("Individual owner not found with siret: {} not found", query.getNip());
            return null;
        }
        log.info("individual owner with nip: {} found", owner);
        return DtoFactory.fromOwner(owner);
    }

    @QueryHandler
    public List<OwnerResponseDTO> handle(GetIndividualOwnerByKeywordQuery query){
        log.info("Handling GetIndividualOwnerByKeywordQuery: {}", query);
        String keyword = "%"+query.getKeyword()+"%";
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        List<Owner> owners = ownerRepository.findIndividualOwnerByKeyword(keyword, pageable).getContent();
        log.info("individual owner with keyword: {} found", keyword);
        return DtoFactory.fromListOfOwners(owners);
    }

}
