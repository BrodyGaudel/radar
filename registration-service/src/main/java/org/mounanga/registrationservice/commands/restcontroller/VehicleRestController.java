package org.mounanga.registrationservice.commands.restcontroller;

import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.mounanga.registrationservice.commands.command.vehicle.CreateVehicleCommand;
import org.mounanga.registrationservice.commands.command.vehicle.DeleteVehicleCommand;
import org.mounanga.registrationservice.commands.command.vehicle.UpdateVehicleCommand;
import org.mounanga.registrationservice.commands.dto.VehicleRequestDTO;
import org.mounanga.registrationservice.commands.util.factory.CommandFactory;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/vehicles")
public class VehicleRestController {

    private final CommandGateway commandGateway;

    public VehicleRestController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public CompletableFuture<String> createVehicle(@RequestBody @Valid VehicleRequestDTO dto) {
        CreateVehicleCommand command = CommandFactory.create(dto);
        return commandGateway.send(command);
    }

    @PutMapping("/update/{id}")
    public CompletableFuture<String> updateVehicle(@PathVariable String id,@RequestBody @Valid VehicleRequestDTO dto) {
        UpdateVehicleCommand command = CommandFactory.create(id, dto);
        return commandGateway.send(command);
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<String> deleteVehicle(@PathVariable String id) {
        DeleteVehicleCommand command = CommandFactory.createDeleteVehicleCommand(id);
        return commandGateway.send(command);
    }
}
