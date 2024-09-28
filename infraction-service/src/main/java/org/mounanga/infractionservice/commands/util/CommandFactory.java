package org.mounanga.infractionservice.commands.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mounanga.infractionservice.commands.command.CreateInfractionCommand;
import org.mounanga.infractionservice.commands.command.DeleteInfractionCommand;
import org.mounanga.infractionservice.commands.command.UpdateInfractionCommand;
import org.mounanga.infractionservice.commands.dto.InfractionRequestDTO;

import java.util.UUID;

/**
 * A factory class to create various types of commands related to infractions.
 * This class provides static methods to create instances of the command objects
 * for creating, updating, and deleting infractions.
 *
 * <p>
 * Each method uses the provided data to generate a specific command object.
 * This class cannot be instantiated.
 * </p>
 */
public class CommandFactory {

    /**
     * Private constructor to prevent instantiation of this factory class.
     */
    private CommandFactory() {
        super();
    }

    /**
     * Creates a new {@link CreateInfractionCommand} based on the provided {@link InfractionRequestDTO}.
     *
     * @param dto The data transfer object containing infraction details.
     * @return A new instance of {@link CreateInfractionCommand}.
     */
    @NotNull
    @Contract("_ -> new")
    public static CreateInfractionCommand create(@NotNull final InfractionRequestDTO dto) {
        return new CreateInfractionCommand(
                UUID.randomUUID().toString(),
                dto.dateTime(),
                dto.speed(),
                dto.amount(),
                dto.vehicleId(),
                dto.radarId()
        );
    }

    /**
     * Creates a new {@link UpdateInfractionCommand} for updating an existing infraction.
     *
     * @param id  The unique identifier of the infraction to update.
     * @param dto The data transfer object containing the updated infraction details.
     * @return A new instance of {@link UpdateInfractionCommand}.
     *
     */
    @NotNull
    @Contract("_, _ -> new")
    public static UpdateInfractionCommand create(@NotNull final String id, @NotNull final InfractionRequestDTO dto) {
        return new UpdateInfractionCommand(
                id,
                dto.dateTime(),
                dto.speed(),
                dto.amount(),
                dto.vehicleId(),
                dto.radarId()
        );
    }

    /**
     * Creates a new {@link DeleteInfractionCommand} to delete an existing infraction.
     *
     * @param id The unique identifier of the infraction to delete.
     * @return A new instance of {@link DeleteInfractionCommand}.
     *
     */
    @NotNull
    @Contract("_ -> new")
    public static DeleteInfractionCommand create(@NotNull final String id) {
        return new DeleteInfractionCommand(id);
    }
}
