package org.mounanga.infractionservice.queries.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.infractionservice.queries.dto.InfractionResponseDTO;
import org.mounanga.infractionservice.queries.entity.Infraction;
import org.mounanga.infractionservice.queries.query.GetInfractionByIdQuery;
import org.mounanga.infractionservice.queries.query.GetInfractionByRadarIdQuery;
import org.mounanga.infractionservice.queries.query.GetInfractionByVehicleIdQuery;
import org.mounanga.infractionservice.queries.repository.InfractionRepository;
import org.mounanga.infractionservice.queries.util.DtoFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InfractionQueryHandlerService {

    private final InfractionRepository infractionRepository;

    public InfractionQueryHandlerService(InfractionRepository infractionRepository) {
        this.infractionRepository = infractionRepository;
    }

    @QueryHandler
    public InfractionResponseDTO handle(@NotNull GetInfractionByIdQuery query){
        log.info("Handling GetInfractionByIdQuery");
        Infraction infraction = infractionRepository.findById(query.getId()).orElse(null);
        if(infraction == null){
            log.warn("Infraction not found for query {}", query.getId());
            return null;
        }
        log.info("Infraction found for query {}", query.getId());
        return DtoFactory.create(infraction);
    }

    @QueryHandler
    public List<InfractionResponseDTO> handle(@NotNull GetInfractionByRadarIdQuery query){
        log.info("Handling GetInfractionByRadarIdQuery");
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        List<Infraction> infractions = infractionRepository.findByRadarId(query.getRadarId(), query.getStart(), query.getEnd(), pageable).getContent();
        log.info("{} infractions found by radar", infractions.size());
        return DtoFactory.createList(infractions);
    }

    @QueryHandler
    public List<InfractionResponseDTO> handle(@NotNull GetInfractionByVehicleIdQuery query){
        log.info("Handling GetInfractionByVehicleIdQuery");
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        List<Infraction> infractions = infractionRepository.findByVehicleId(query.getVehicleId(), query.getStart(), query.getEnd(), pageable).getContent();
        log.info("{} infractions found by vehicle", infractions.size());
        return DtoFactory.createList(infractions);
    }
}
