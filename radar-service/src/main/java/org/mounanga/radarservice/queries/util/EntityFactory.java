package org.mounanga.radarservice.queries.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mounanga.radarservice.common.event.RadarCreatedEvent;
import org.mounanga.radarservice.common.event.RadarUpdatedEvent;
import org.mounanga.radarservice.queries.entity.Radar;

/**
 * This class provides factory methods for creating or updating {@link Radar} entities.
 * It converts domain events such as {@link RadarCreatedEvent} and {@link RadarUpdatedEvent}
 * into {@link Radar} instances by populating the entity with event data.
 * The class contains static methods for:
 * - Creating a new {@link Radar} entity from a {@link RadarCreatedEvent}.
 * - Updating an existing {@link Radar} entity using a {@link RadarUpdatedEvent}.
 */
public class EntityFactory {

    /**
     * Private constructor to prevent instantiation of the factory class.
     * This class only provides static methods for entity creation.
     */
    private EntityFactory() {
        super();
    }

    /**
     * Creates a new {@link Radar} entity from the given {@link RadarCreatedEvent}.
     * This method extracts data from the event, such as radar ID, address, latitude, longitude, and speed limit,
     * and uses it to build a new {@link Radar} instance.
     *
     * @param event a {@link RadarCreatedEvent} containing the radar details such as ID, address, latitude, longitude, and speed limit.
     * @return a new {@link Radar} entity populated with the event data.
     */
    public static Radar create(@NotNull final RadarCreatedEvent event) {
        return Radar.builder()
                .id(event.getEventId())
                .address(event.getAddress())
                .latitude(event.getLatitude())
                .longitude(event.getLongitude())
                .speedLimit(event.getSpeedLimit())
                .build();
    }

    /**
     * Updates an existing {@link Radar} entity using the details from a {@link RadarUpdatedEvent}.
     * This method modifies the radar entity in place by setting new values from the event, such as updated address,
     * latitude, longitude, and speed limit.
     *
     * @param event a {@link RadarUpdatedEvent} containing the updated radar details such as ID, address, latitude, longitude, and speed limit.
     * @param radar an existing {@link Radar} entity that needs to be updated with the new values.
     * @return the updated {@link Radar} entity.
     */
    @NotNull
    @Contract("_, _ -> param2")
    public static Radar update(@NotNull final RadarUpdatedEvent event, @NotNull Radar radar) {
        radar.setId(event.getEventId());
        radar.setAddress(event.getAddress());
        radar.setLatitude(event.getLatitude());
        radar.setLongitude(event.getLongitude());
        radar.setSpeedLimit(event.getSpeedLimit());
        return radar;
    }
}