package org.mounanga.radarcommandservice.util.publisher;

import org.mounanga.radarcommandservice.event.RadarCreatedEvent;
import org.mounanga.radarcommandservice.event.RadarDeletedEvent;
import org.mounanga.radarcommandservice.event.RadarUpdatedEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "radar-query-service")
public interface EventRestClient {

    @PostMapping("/radar/queries/events/create")
    void sendRadarCreatedEvent(@RequestBody RadarCreatedEvent event);

    @PostMapping("/radar/queries/events/update")
    void sendRadarUpdatedEvent(@RequestBody RadarUpdatedEvent event);

    @PostMapping("/radar/queries/events/delete")
    void sendRadarDeletedEvent(@RequestBody RadarDeletedEvent event);
}
