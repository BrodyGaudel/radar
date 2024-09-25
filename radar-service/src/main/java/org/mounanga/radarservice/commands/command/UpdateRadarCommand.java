package org.mounanga.radarservice.commands.command;

import lombok.Getter;

@Getter
public class UpdateRadarCommand extends BaseCommand<String> {

    private final String address;
    private final Double latitude;
    private final Double longitude;
    private final Double speedLimit;

    public UpdateRadarCommand(String commandId, String address, Double latitude, Double longitude, Double speedLimit) {
        super(commandId);
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speedLimit = speedLimit;
    }
}
