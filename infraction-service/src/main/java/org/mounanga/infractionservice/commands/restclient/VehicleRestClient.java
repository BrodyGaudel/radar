package org.mounanga.infractionservice.commands.restclient;


import org.mounanga.infractionservice.commands.dto.VehicleResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vehicle-service")
public interface VehicleRestClient {

    @GetMapping("/radar/queries/vehicles/get/{id}")
    VehicleResponseDTO getById(@PathVariable String id);
}