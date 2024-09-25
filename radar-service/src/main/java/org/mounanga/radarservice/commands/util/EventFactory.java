package org.mounanga.radarservice.commands.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mounanga.radarservice.commands.command.CreateRadarCommand;
import org.mounanga.radarservice.commands.command.DeleteRadarCommand;
import org.mounanga.radarservice.commands.command.UpdateRadarCommand;
import org.mounanga.radarservice.common.event.RadarCreatedEvent;
import org.mounanga.radarservice.common.event.RadarDeletedEvent;
import org.mounanga.radarservice.common.event.RadarUpdatedEvent;

/**
 * A utility class for creating radar-related events from the corresponding radar commands.
 * <p>
 * This class provides static methods to create instances of {@link RadarCreatedEvent}, {@link RadarUpdatedEvent},
 * and {@link RadarDeletedEvent} based on command objects like {@link CreateRadarCommand}, {@link UpdateRadarCommand},
 * and {@link DeleteRadarCommand}.
 * <p>
 * The class ensures the correct mapping between command properties and the associated events.
 */
public class EventFactory {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private EventFactory() {
        super();
    }

    /**
     * Creates a {@link RadarCreatedEvent} from the given {@link CreateRadarCommand}.
     * <p>
     * The created event will contain the same data as the command, including the radar's address, latitude,
     * longitude, and speed limit.
     *
     * @param command The {@link CreateRadarCommand} used to generate the radar created event.
     * @return A new {@link RadarCreatedEvent} with the details of the created radar.
     */
    @NotNull
    @Contract("_ -> new")
    public static RadarCreatedEvent create(@NotNull CreateRadarCommand command) {
        return new RadarCreatedEvent(
                command.getCommandId(),
                command.getAddress(),
                command.getLatitude(),
                command.getLongitude(),
                command.getSpeedLimit()
        );
    }

    /**
     * Creates a {@link RadarUpdatedEvent} from the given {@link UpdateRadarCommand}.
     * <p>
     * The created event will contain the updated radar details such as the new address, latitude, longitude,
     * and speed limit.
     *
     * @param command The {@link UpdateRadarCommand} used to generate the radar updated event.
     * @return A new {@link RadarUpdatedEvent} with the updated radar details.
     */
    @NotNull
    @Contract("_ -> new")
    public static RadarUpdatedEvent create(@NotNull UpdateRadarCommand command) {
        return new RadarUpdatedEvent(
                command.getCommandId(),
                command.getAddress(),
                command.getLatitude(),
                command.getLongitude(),
                command.getSpeedLimit()
        );
    }

    /**
     * Creates a {@link RadarDeletedEvent} from the given {@link DeleteRadarCommand}.
     * <p>
     * The created event will contain only the command ID, as no additional information is needed to delete a radar.
     *
     * @param command The {@link DeleteRadarCommand} used to generate the radar deleted event.
     * @return A new {@link RadarDeletedEvent} with the ID of the radar to be deleted.
     */
    @NotNull
    @Contract("_ -> new")
    public static RadarDeletedEvent create(@NotNull DeleteRadarCommand command) {
        return new RadarDeletedEvent(command.getCommandId());
    }
}
