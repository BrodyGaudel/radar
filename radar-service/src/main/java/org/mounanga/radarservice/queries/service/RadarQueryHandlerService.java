package org.mounanga.radarservice.queries.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.radarservice.queries.dto.RadarResponseDTO;
import org.mounanga.radarservice.queries.entity.Radar;
import org.mounanga.radarservice.queries.query.GetRadarByAddressQuery;
import org.mounanga.radarservice.queries.query.GetRadarByIdQuery;
import org.mounanga.radarservice.queries.query.GetRadarByLatitudeAndLongitudeQuery;
import org.mounanga.radarservice.queries.repository.RadarRepository;
import org.mounanga.radarservice.queries.util.DtoFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RadarQueryHandlerService {

    private final RadarRepository radarRepository;

    public RadarQueryHandlerService(RadarRepository radarRepository) {
        this.radarRepository = radarRepository;
    }

    @QueryHandler
    public RadarResponseDTO handle(@NotNull GetRadarByIdQuery query){
        log.info("Handling GetRadarByIdQuery");
        Radar radar = radarRepository.findById(query.getRadarId()).orElse(null);
        if(radar == null){
            log.info(String.format("Radar with id %s not found", query.getRadarId()));
            return null;
        }
        log.info("radar found");
        return DtoFactory.from(radar);
    }

    @QueryHandler
    public List<RadarResponseDTO> handle(@NotNull GetRadarByAddressQuery query){
        log.info("Handling GetRadarByAddressQuery");
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        String keyword = "%"+query.getAddress()+"%";
        List<Radar> radars = radarRepository.findByAddress(keyword,pageable).getContent();
        log.info("{} radars found for address {}", radars.size(), query.getAddress());
        return DtoFactory.fromList(radars);
    }

    @QueryHandler
    public List<RadarResponseDTO> handle(@NotNull GetRadarByLatitudeAndLongitudeQuery query){
        log.info("Handling GetRadarByLatitudeAndLongitudeQuery");
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        List<Radar> radars = radarRepository.findByLatitudeAndLongitude(
                query.getLatitude(), query.getLongitude(), pageable
        ).getContent();
        log.info("{} radars found for latitude '{}' and longitude '{}'", radars.size(),query.getLatitude(), query.getLongitude());
        return DtoFactory.fromList(radars);
    }
}
