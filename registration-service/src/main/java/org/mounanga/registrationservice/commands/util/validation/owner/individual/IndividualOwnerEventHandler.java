package org.mounanga.registrationservice.commands.util.validation.owner.individual;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.common.event.owner.individual.IndividualOwnerCreatedEvent;
import org.mounanga.registrationservice.common.event.owner.individual.IndividualOwnerDeletedEvent;
import org.mounanga.registrationservice.common.event.owner.individual.IndividualOwnerUpdatedEvent;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("ownerIndividualNip")
public class IndividualOwnerEventHandler {

    @EventHandler
    public void on(@NotNull IndividualOwnerCreatedEvent event, @NotNull IndividualOwnerNipRepository repository){
        IndividualOwnerNip individualOwnerNip = new IndividualOwnerNip(event.getEventId(), event.getNip());
        repository.save(individualOwnerNip);
    }

    @EventHandler
    public void on(@NotNull IndividualOwnerUpdatedEvent event, @NotNull IndividualOwnerNipRepository repository){
        IndividualOwnerNip individualOwnerNip = repository.findById(event.getEventId()).orElse(null);
        if(individualOwnerNip != null){
            individualOwnerNip.setNip(event.getNip());
            repository.save(individualOwnerNip);
        }
    }

    @EventHandler
    public void on(@NotNull IndividualOwnerDeletedEvent event, @NotNull IndividualOwnerNipRepository  repository){
        repository.deleteById(event.getEventId());
    }
}
