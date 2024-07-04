package org.mounanga.radarservice.commands.aggregate;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mounanga.radarservice.commands.command.CreateRadarCommand;
import org.mounanga.radarservice.commands.command.DeleteRadarCommand;
import org.mounanga.radarservice.commands.command.UpdateRadarCommand;
import org.mounanga.radarservice.common.event.RadarCreatedEvent;
import org.mounanga.radarservice.common.event.RadarDeletedEvent;
import org.mounanga.radarservice.common.event.RadarUpdatedEvent;
import org.mounanga.radarservice.common.exception.NegativeSpeedException;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;


@SpringBootTest
class RadarAggregateTest {

    private AggregateTestFixture<RadarAggregate> fixture;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(RadarAggregate.class);
    }

    @Test
    void testCreateRadar() {
        String radarId = UUID.randomUUID().toString();
        CreateRadarCommand command = new CreateRadarCommand(radarId, 100.0, "123 Street", 45.0, 45.0);
        RadarCreatedEvent event = new RadarCreatedEvent(radarId, 100.0, "123 Street", 45.0, 45.0);

        fixture.givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    void testUpdateRadar() {
        String radarId = UUID.randomUUID().toString();
        RadarCreatedEvent createEvent = new RadarCreatedEvent(radarId, 100.0, "123 Street", 45.0, 45.0);

        UpdateRadarCommand updateCommand = new UpdateRadarCommand(radarId, 120.0, "456 Avenue", 50.0, 50.0);
        RadarUpdatedEvent updateEvent = new RadarUpdatedEvent(radarId, 120.0, "456 Avenue", 50.0, 50.0);

        fixture.given(createEvent)
                .when(updateCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(updateEvent);
    }

    @Test
    void testDeleteRadar() {
        String radarId = UUID.randomUUID().toString();
        RadarCreatedEvent createEvent = new RadarCreatedEvent(radarId, 100.0, "123 Street", 45.0, 45.0);

        DeleteRadarCommand deleteCommand = new DeleteRadarCommand(radarId);
        RadarDeletedEvent deleteEvent = new RadarDeletedEvent(radarId);

        fixture.given(createEvent)
                .when(deleteCommand)
                .expectSuccessfulHandlerExecution()
                .expectEvents(deleteEvent);
    }

    @Test
    void testCreateRadarNegativeSpeedException() {
        String radarId = UUID.randomUUID().toString();
        CreateRadarCommand command = new CreateRadarCommand(radarId, -100.0, "123 Street", 45.0, 45.0);

        fixture.givenNoPriorActivity()
                .when(command)
                .expectException(NegativeSpeedException.class);
    }

    @Test
    void testUpdateRadarNegativeSpeedException() {
        String radarId = UUID.randomUUID().toString();
        RadarCreatedEvent createEvent = new RadarCreatedEvent(radarId, 100.0, "123 Street", 45.0, 45.0);

        UpdateRadarCommand updateCommand = new UpdateRadarCommand(radarId, -120.0, "456 Avenue", 50.0, 50.0);

        fixture.given(createEvent)
                .when(updateCommand)
                .expectException(NegativeSpeedException.class);
    }

}