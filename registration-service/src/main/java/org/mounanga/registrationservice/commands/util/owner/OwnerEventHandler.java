package org.mounanga.registrationservice.commands.util.owner;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.common.event.owner.OwnerCreatedEvent;
import org.mounanga.registrationservice.common.event.owner.OwnerDeletedEvent;
import org.mounanga.registrationservice.common.event.owner.OwnerUpdatedEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("ownerCin")
public class OwnerEventHandler {

    @EventHandler
    public void on(@NotNull OwnerCreatedEvent event, @NotNull OwnerCinRepository repository){
        repository.save(new OwnerCin(event.getId(), event.getCin()));
    }

    @EventHandler
    public void on(@NotNull OwnerUpdatedEvent event, @NotNull OwnerCinRepository repository) {
        Optional<OwnerCin> ownerCin = repository.findById(event.getId());
        if (ownerCin.isPresent()) {
            ownerCin.get().setCin(event.getCin());
            repository.save(ownerCin.get());
        }
    }

    @EventHandler
    public void on(@NotNull OwnerDeletedEvent event, @NotNull OwnerCinRepository repository){
        repository.deleteById(event.getId());
    }
}
