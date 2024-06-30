package org.mounanga.registrationservice.commands.command.vehicle;

import org.mounanga.registrationservice.commands.command.BaseCommand;

public class DeleteVehicleCommand extends BaseCommand<String> {

    public DeleteVehicleCommand(String id) {
        super(id);
    }
}
