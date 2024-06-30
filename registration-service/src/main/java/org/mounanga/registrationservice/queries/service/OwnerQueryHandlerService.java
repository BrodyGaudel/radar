package org.mounanga.registrationservice.queries.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.queries.dto.OwnerResponseDTO;
import org.mounanga.registrationservice.queries.entity.Owner;
import org.mounanga.registrationservice.queries.query.GetAllOwnersQuery;
import org.mounanga.registrationservice.queries.query.GetOwnerByIdQuery;
import org.mounanga.registrationservice.queries.query.SearchOwnerQuery;
import org.mounanga.registrationservice.queries.repository.OwnerRepository;
import org.mounanga.registrationservice.queries.util.Mappers;
import org.springframework.data.domain.Page;
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
    public List<OwnerResponseDTO> handle(@NotNull GetAllOwnersQuery query){
        log.info("# GetAllOwnersQuery handled");
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        Page<Owner> owners = ownerRepository.findAll(pageable);
        log.info("# {} owner(s) found", owners.getTotalElements());
        return owners.getContent().stream().map(Mappers::fromOwner).toList();
    }

    @QueryHandler
    public OwnerResponseDTO handle(@NotNull GetOwnerByIdQuery query){
        log.info("# GetOwnerByIdQuery handled");
        Owner owner = ownerRepository.findById(query.getId()).orElse(null);
        if(owner == null){
            log.warn("# Owner not found for id {}", query.getId());
            return null;
        }
        log.info("# Owner found for id {}", query.getId());
        return Mappers.fromOwner(owner);
    }

    @QueryHandler
    public List<OwnerResponseDTO> handle(@NotNull SearchOwnerQuery query){
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        String keyword = "%"+query.getKeyword()+"%";
        Page<Owner> ownerPage = ownerRepository.search(keyword, pageable);
        log.info("# {} owner(s) found in page {}", ownerPage.getTotalElements(), query.getPage());
        return ownerPage.getContent().stream().map(Mappers::fromOwner).toList();
    }
}
