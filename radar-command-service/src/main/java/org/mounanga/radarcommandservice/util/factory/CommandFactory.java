package org.mounanga.radarcommandservice.util.factory;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mounanga.radarcommandservice.command.CreateRadarCommand;
import org.mounanga.radarcommandservice.command.DeleteRadarCommand;
import org.mounanga.radarcommandservice.command.UpdateRadarCommand;
import org.mounanga.radarcommandservice.dto.RadarRequestDTO;


import java.util.UUID;

/**
 * This class is responsible for creating instances of various command objects used in radar operations, such as creating, updating,
 * and deleting radar instances. The class provides static factory methods for each type of command.

 * It includes factory methods for:
 * - {@link CreateRadarCommand} for creating new radar instances.
 * - {@link UpdateRadarCommand} for updating existing radar instances.
 * - {@link DeleteRadarCommand} for deleting radar instances.

 * The factory methods use {@link RadarRequestDTO} as input for creating radar commands and automatically generate a unique ID where needed.
 */
public class CommandFactory {

    /**
     * Private constructor to prevent instantiation of the factory class.
     * This class is not intended to be instantiated as it only provides static methods.
     */
    private CommandFactory() {
        super();
    }

    /**
     * Creates a new {@link CreateRadarCommand} for creating a radar.
     * This method generates a new unique radar ID using {@link UUID#randomUUID()} and extracts radar details from the provided {@link RadarRequestDTO}.
     *
     * @param dto a {@link RadarRequestDTO} containing the radar details such as address, latitude, longitude, and speed limit.
     * @return a new {@link CreateRadarCommand} instance with the radar details.
     */
    @NotNull
    @Contract("_ -> new")
    public static CreateRadarCommand create(@NotNull final RadarRequestDTO dto) {
        return new CreateRadarCommand(
                UUID.randomUUID().toString(),
                dto.address(),
                dto.latitude(),
                dto.longitude(),
                dto.speedLimit()
        );
    }

    /**
     * Creates a new {@link UpdateRadarCommand} for updating an existing radar.
     * This method uses the provided radar ID and extracts updated radar details from the {@link RadarRequestDTO}.
     *
     * @param id  the unique identifier of the radar to update.
     * @param dto a {@link RadarRequestDTO} containing the updated radar details such as address, latitude, longitude, and speed limit.
     * @return a new {@link UpdateRadarCommand} instance with the updated radar details.
     */
    @NotNull
    @Contract("_, _ -> new")
    public static UpdateRadarCommand create(@NotNull final String id, @NotNull final RadarRequestDTO dto) {
        return new UpdateRadarCommand(
                id,
                dto.address(),
                dto.latitude(),
                dto.longitude(),
                dto.speedLimit()
        );
    }

    /**
     * Creates a new {@link DeleteRadarCommand} for deleting a radar.
     * This method uses the provided radar ID to create a delete command.
     *
     * @param id the unique identifier of the radar to delete.
     * @return a new {@link DeleteRadarCommand} instance.
     */
    @NotNull
    @Contract("_ -> new")
    public static DeleteRadarCommand create(@NotNull final String id) {
        return new DeleteRadarCommand(id);
    }
}
