package org.mounanga.registrationservice.commands.command.vehicle;

import lombok.Getter;
import org.mounanga.registrationservice.commands.command.BaseCommand;

@Getter
public class CreateVehicleCommand extends BaseCommand<String> {

    private final String registrationId;
    private final String marque;
    private final String model;
    private final Double taxPower;
    private final String description;
    private final String ownerId;

    public CreateVehicleCommand(String commandId, String registrationId, String marque, String model, Double taxPower, String description, String ownerId) {
        super(commandId);
        this.registrationId = registrationId;
        this.marque = marque;
        this.model = model;
        this.taxPower = taxPower;
        this.description = description;
        this.ownerId = ownerId;
    }
}
