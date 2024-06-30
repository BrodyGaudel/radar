package org.mounanga.registrationservice.commands.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.mounanga.registrationservice.commands.command.vehicle.CreateVehicleCommand;
import org.mounanga.registrationservice.commands.command.vehicle.DeleteVehicleCommand;
import org.mounanga.registrationservice.commands.command.vehicle.UpdateVehicleCommand;
import org.mounanga.registrationservice.commands.dto.VehicleRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/vehicles")
public class VehicleCommandRestController {

    private final CommandGateway commandGateway;

    public VehicleCommandRestController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public CompletableFuture<String> create(@RequestBody VehicleRequestDTO dto){
        return commandGateway.send( new CreateVehicleCommand(
                UUID.randomUUID().toString(),
                dto.getRegistrationId(),
                dto.getMarque(),
                dto.getModel(),
                dto.getDescription(),
                dto.getTaxPower(),
                dto.getOwnerId()
        ));
    }

    @PutMapping("/update/{id}")
    public CompletableFuture<String> update(@PathVariable String id, @RequestBody VehicleRequestDTO dto){
        return commandGateway.send(new UpdateVehicleCommand(
                id,
                dto.getRegistrationId(),
                dto.getMarque(),
                dto.getModel(),
                dto.getDescription(),
                dto.getTaxPower(),
                dto.getOwnerId()
        ));
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<String> delete(@PathVariable String id){
        return commandGateway.send(new DeleteVehicleCommand(id));
    }
}
