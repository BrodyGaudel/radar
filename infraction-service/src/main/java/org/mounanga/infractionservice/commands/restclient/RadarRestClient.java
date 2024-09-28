package org.mounanga.infractionservice.commands.restclient;

import org.mounanga.infractionservice.commands.dto.RadarResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "owner-service")
public interface RadarRestClient {

    @GetMapping("/radar/queries/radars/get/{id}")
    RadarResponseDTO getById(@PathVariable String id);
}