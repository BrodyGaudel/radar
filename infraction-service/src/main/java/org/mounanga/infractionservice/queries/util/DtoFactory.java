package org.mounanga.infractionservice.queries.util;

import org.jetbrains.annotations.NotNull;
import org.mounanga.infractionservice.queries.dto.InfractionResponseDTO;
import org.mounanga.infractionservice.queries.entity.Infraction;

import java.util.List;

public class DtoFactory {

    private DtoFactory() {
        super();
    }

    @NotNull
    public static InfractionResponseDTO create(@NotNull Infraction infraction) {
        final InfractionResponseDTO dto = new InfractionResponseDTO();
        dto.setId(infraction.getId());
        dto.setAmount(infraction.getAmount());
        dto.setSpeed(infraction.getSpeed());
        dto.setDateTime(infraction.getDateTime());
        dto.setRadarId(infraction.getRadarId());
        dto.setVehicleId(infraction.getVehicleId());
        dto.setCreatedDate(infraction.getCreatedDate());
        dto.setCreatedBy(infraction.getCreatedBy());
        dto.setLastModifiedDate(infraction.getLastModifiedDate());
        dto.setLastModifiedBy(infraction.getLastModifiedBy());
        return dto;
    }

    public static List<InfractionResponseDTO> createList(@NotNull List<Infraction> infractions) {
        return infractions.stream().map(DtoFactory::create).toList();
    }
}
