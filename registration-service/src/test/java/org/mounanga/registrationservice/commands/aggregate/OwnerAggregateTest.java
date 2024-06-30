package org.mounanga.registrationservice.commands.aggregate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mounanga.registrationservice.commands.command.owner.CreateOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.DeleteOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.UpdateOwnerCommand;
import org.mounanga.registrationservice.common.enums.Gender;
import org.mounanga.registrationservice.common.event.owner.OwnerCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.mounanga.registrationservice.common.event.owner.OwnerDeletedEvent;
import org.mounanga.registrationservice.common.event.owner.OwnerUpdatedEvent;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


@SpringBootTest
class OwnerAggregateTest {

    private AggregateTestFixture<OwnerAggregate> fixture;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(OwnerAggregate.class);
    }

    @Test
    void testCreateOwnerCommand() {
        String ownerId = "owner-123";
        CreateOwnerCommand command = new CreateOwnerCommand(
                ownerId,
                "John",
                "Doe",
                LocalDate.of(1990, 1, 1),
                "New York",
                Gender.M,
                "American",
                "ABC12345"
        );

        OwnerCreatedEvent event = new OwnerCreatedEvent(
                ownerId,
                "John",
                "Doe",
                LocalDate.of(1990, 1, 1),
                "New York",
                Gender.M,
                "American",
                "ABC12345"
        );

        fixture.givenNoPriorActivity()
                .when(command)
                .expectEvents(event);
    }

    @Test
    void testUpdateOwnerCommand() {
        String ownerId = "owner-123";
        UpdateOwnerCommand command = new UpdateOwnerCommand(
                ownerId,
                "Jane",
                "Doe",
                LocalDate.of(1992, 2, 2),
                "Los Angeles",
                Gender.F,
                "American",
                "XYZ67890"
        );

        OwnerUpdatedEvent event = new OwnerUpdatedEvent(
                ownerId,
                "Jane",
                "Doe",
                LocalDate.of(1992, 2, 2),
                "Los Angeles",
                Gender.F,
                "American",
                "XYZ67890"
        );

        fixture.given(new OwnerUpdatedEvent(
                        ownerId,
                        "John",
                        "Doe",
                        LocalDate.of(1990, 1, 1),
                        "New York",
                        Gender.M,
                        "American",
                        "ABC12345"))
                .when(command)
                .expectEvents(event);
    }

    @Test
    void testDeleteOwnerCommand() {
        String ownerId = "owner-123";
        DeleteOwnerCommand command = new DeleteOwnerCommand(ownerId);

        OwnerDeletedEvent event = new OwnerDeletedEvent(ownerId);

        fixture.given(new OwnerCreatedEvent(
                        ownerId,
                        "John",
                        "Doe",
                        LocalDate.of(1990, 1, 1),
                        "New York",
                        Gender.M,
                        "American",
                        "ABC12345"))
                .when(command)
                .expectEvents(event);
    }


}