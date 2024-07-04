package org.mounanga.radarservice.commands.controller;

import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.mounanga.radarservice.commands.command.CreateRadarCommand;
import org.mounanga.radarservice.commands.command.DeleteRadarCommand;
import org.mounanga.radarservice.commands.command.UpdateRadarCommand;
import org.mounanga.radarservice.commands.dto.RadarRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/radars/commands")
public class RadarCommandRestController {

    private final CommandGateway commandGateway;

    public RadarCommandRestController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public CompletableFuture<String> createRadar(@RequestBody @Valid RadarRequestDTO dto) {
        return commandGateway.send(new CreateRadarCommand(
                UUID.randomUUID().toString(),
                dto.speedLimit(),
                dto.address(),
                dto.longitude(),
                dto.latitude()
        ));
    }

    @PutMapping("/update/{id}")
    public CompletableFuture<String> updateRadar(@PathVariable String id,@RequestBody @Valid RadarRequestDTO dto) {
        return commandGateway.send(new UpdateRadarCommand(
                id,
                dto.speedLimit(),
                dto.address(),
                dto.longitude(),
                dto.latitude()
        ));
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<String> deleteRadar(@PathVariable String id) {
        return commandGateway.send(new DeleteRadarCommand(id));
    }
}
