package org.mounanga.radarqueryservice.restcontroller;

import org.mounanga.radarqueryservice.event.RadarCreatedEvent;
import org.mounanga.radarqueryservice.event.RadarDeletedEvent;
import org.mounanga.radarqueryservice.event.RadarUpdatedEvent;
import org.mounanga.radarqueryservice.service.EventService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/queries/events")
public class EventRestController {

    private final EventService eventService;

    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }


    @PostMapping("/create")
    public void receiveRadarCreatedEvent(@RequestBody RadarCreatedEvent event) {
        eventService.on(event);
    }

    @PostMapping("/update")
    public void receiveRadarUpdatedEvent(@RequestBody RadarUpdatedEvent event) {
        eventService.on(event);
    }

    @PostMapping("/delete")
    public void receiveRadarDeletedEvent(@RequestBody RadarDeletedEvent event) {
        eventService.on(event);
    }

}
