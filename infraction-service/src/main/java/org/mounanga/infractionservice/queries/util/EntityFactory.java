package org.mounanga.infractionservice.queries.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mounanga.infractionservice.common.event.InfractionCreatedEvent;
import org.mounanga.infractionservice.common.event.InfractionUpdatedEvent;
import org.mounanga.infractionservice.queries.entity.Infraction;

/**
 * A factory class to create and update {@link Infraction} entities based on the provided events.
 * This class provides static methods for creating new infractions or updating existing ones
 * based on the respective event objects.
 *
 * <p>
 * This class cannot be instantiated.
 * </p>
 */
public class EntityFactory {

    /**
     * Private constructor to prevent instantiation of this factory class.
     */
    private EntityFactory() {
        super();
    }

    /**
     * Creates a new {@link Infraction} entity based on the given {@link InfractionCreatedEvent}.
     *
     * @param event The event object containing the details of the infraction to be created.
     * @return A newly created {@link Infraction} entity.
     *
     */
    @NotNull
    public static Infraction create(@NotNull InfractionCreatedEvent event) {
        final Infraction infraction = new Infraction();
        infraction.setId(event.getId());
        infraction.setAmount(event.getAmount());
        infraction.setSpeed(event.getSpeed());
        infraction.setDateTime(event.getDateTime());
        infraction.setRadarId(event.getRadarId());
        infraction.setVehicleId(event.getVehicleId());
        return infraction;
    }

    /**
     * Updates an existing {@link Infraction} entity with the details from the {@link InfractionUpdatedEvent}.
     *
     * <p>
     * The method updates the old infraction entity by setting its properties to the values
     * from the provided event object.
     * </p>
     *
     * @param event The event object containing the updated infraction details.
     * @param oldInfraction The existing {@link Infraction} entity to update.
     * @return The updated {@link Infraction} entity.
     *
     */
    @NotNull
    @Contract("_, _ -> param2")
    public static Infraction update(@NotNull InfractionUpdatedEvent event, @NotNull Infraction oldInfraction) {
        oldInfraction.setAmount(event.getAmount());
        oldInfraction.setSpeed(event.getSpeed());
        oldInfraction.setDateTime(event.getDateTime());
        oldInfraction.setRadarId(event.getRadarId());
        oldInfraction.setVehicleId(event.getVehicleId());
        return oldInfraction;
    }
}
