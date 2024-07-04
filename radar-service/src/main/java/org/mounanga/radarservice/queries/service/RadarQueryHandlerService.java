package org.mounanga.radarservice.queries.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.radarservice.queries.dto.RadarResponseDTO;
import org.mounanga.radarservice.queries.entity.Radar;
import org.mounanga.radarservice.queries.query.GetAllRadarsQuery;
import org.mounanga.radarservice.queries.query.GetRadarByAddressDetailsQuery;
import org.mounanga.radarservice.queries.query.GetRadarByIdQuery;
import org.mounanga.radarservice.queries.query.SearchRadarByAddressQuery;
import org.mounanga.radarservice.queries.repository.RadarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RadarQueryHandlerService {

    private final RadarRepository radarRepository;

    public RadarQueryHandlerService(RadarRepository radarRepository) {
        this.radarRepository = radarRepository;
    }

    @QueryHandler
    public RadarResponseDTO handle(@NotNull GetRadarByIdQuery query){
        log.info("# GetRadarByIdQuery handled");
        Radar radar = radarRepository.findById(query.getRadarId()).orElse(null);
        if(radar == null){
            log.warn("# Radar not found");
            return null;
        }
        log.info("# Radar found");
        return fromRadar(radar);
    }

    @QueryHandler
    public List<RadarResponseDTO> handle(@NotNull SearchRadarByAddressQuery query){
        log.info("# SearchRadarByAddressQuery handled");
        String address = "%"+query.getAddress()+"%";
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        Page<Radar> radars = radarRepository.findByAddress(address, pageable);
        log.info("# {} Radars found",radars.getTotalElements());
        return radars.getContent().stream().map(this::fromRadar).toList();
    }

    @QueryHandler
    public RadarResponseDTO handle(@NotNull GetRadarByAddressDetailsQuery query){
        log.info("# GetRadarByAddressDetailsQuery handled");
        Radar radar = radarRepository.findByLatitudeAndLongitude(
                query.getLatitude(),
                query.getLongitude()
                ).orElse(null);
        if(radar == null){
            log.warn("# Radar not found '{}'",query);
            return null;
        }
        log.info("# Radar found with details '{}'",query);
        return fromRadar(radar);
    }

    @QueryHandler
    public List<RadarResponseDTO> handle(@NotNull GetAllRadarsQuery query){
        log.info("# GetAllRadarsQuery handled");
        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize());
        Page<Radar> radars = radarRepository.findAll(pageable);
        log.info("# {} Radar(s) found",radars.getTotalElements());
        return radars.getContent().stream().map(this::fromRadar).toList();
    }


    private RadarResponseDTO fromRadar(Radar radar) {
        if(radar == null) {
            return null;
        }
        return RadarResponseDTO.builder()
                .id(radar.getId())
                .address(radar.getAddress())
                .latitude(radar.getLatitude())
                .longitude(radar.getLongitude())
                .speedLimit(radar.getSpeedLimit())
                .createdBy(radar.getCreatedBy())
                .createdDate(radar.getCreatedDate())
                .lastModifiedBy(radar.getLastModifiedBy())
                .lastModifiedDate(radar.getLastModifiedDate())
                .build();
    }


}
