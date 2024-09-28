package org.mounanga.infractionservice.commands.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mounanga.infractionservice.commands.command.CreateInfractionCommand;
import org.mounanga.infractionservice.commands.command.DeleteInfractionCommand;
import org.mounanga.infractionservice.commands.command.UpdateInfractionCommand;
import org.mounanga.infractionservice.common.event.InfractionCreatedEvent;
import org.mounanga.infractionservice.common.event.InfractionDeletedEvent;
import org.mounanga.infractionservice.common.event.InfractionUpdatedEvent;

/**
 * A factory class to create various types of events related to infractions.
 * This class provides static methods to generate event objects for infraction creation,
 * updating, and deletion, based on the corresponding command objects.
 *
 * <p>
 * The factory class cannot be instantiated.
 * </p>
 */
public class EventFactory {

    /**
     * Private constructor to prevent instantiation of this factory class.
     */
    private EventFactory() {
        super();
    }

    /**
     * Creates a new {@link InfractionCreatedEvent} based on the given {@link CreateInfractionCommand}.
     *
     * @param command The command object containing infraction creation details.
     * @return A new instance of {@link InfractionCreatedEvent}.
     *
     */
    @NotNull
    @Contract("_ -> new")
    public static InfractionCreatedEvent create(@NotNull final CreateInfractionCommand command) {
        return new InfractionCreatedEvent(
                command.getId(),
                command.getDateTime(),
                command.getSpeed(),
                command.getAmount(),
                command.getVehicleId(),
                command.getRadarId()
        );
    }

    /**
     * Creates a new {@link InfractionUpdatedEvent} based on the given {@link UpdateInfractionCommand}.
     *
     * @param command The command object containing updated infraction details.
     * @return A new instance of {@link InfractionUpdatedEvent}.
     *
     */
    @NotNull
    @Contract("_ -> new")
    public static InfractionUpdatedEvent create(@NotNull final UpdateInfractionCommand command) {
        return new InfractionUpdatedEvent(
                command.getId(),
                command.getDateTime(),
                command.getSpeed(),
                command.getAmount(),
                command.getVehicleId(),
                command.getRadarId()
        );
    }

    /**
     * Creates a new {@link InfractionDeletedEvent} based on the given {@link DeleteInfractionCommand}.
     *
     * @param command The command object containing the ID of the infraction to delete.
     * @return A new instance of {@link InfractionDeletedEvent}.
     *
     */
    @NotNull
    @Contract("_ -> new")
    public static InfractionDeletedEvent create(@NotNull final DeleteInfractionCommand command) {
        return new InfractionDeletedEvent(command.getId());
    }
}
