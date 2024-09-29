package org.mounanga.radarqueryservice.util.factory;

import org.jetbrains.annotations.NotNull;
import org.mounanga.radarqueryservice.dto.RadarResponseDTO;
import org.mounanga.radarqueryservice.entity.Radar;

import java.util.ArrayList;
import java.util.List;

public class DtoFactory {

    private DtoFactory() {
        super();
    }

    @NotNull
    public static RadarResponseDTO from(@NotNull final Radar radar){
        final RadarResponseDTO dto = new RadarResponseDTO();
        dto.setId(radar.getId());
        dto.setAddress(radar.getAddress());
        dto.setLatitude(radar.getLatitude());
        dto.setLongitude(radar.getLongitude());
        dto.setSpeedLimit(radar.getSpeedLimit());
        dto.setCreateBy(radar.getCreateBy());
        dto.setCreatedDate(radar.getCreatedDate());
        dto.setLastModifiedBy(radar.getLastModifiedBy());
        dto.setLastModifiedDate(radar.getLastModifiedDate());
        return dto;
    }

    public static List<RadarResponseDTO> fromList(@NotNull final List<Radar> radars){
        if(radars.isEmpty()){
            return new ArrayList<>();
        }
        return radars.stream().map(DtoFactory::from).toList();
    }
}
