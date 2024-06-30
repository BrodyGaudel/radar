package org.mounanga.registrationservice.queries.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mounanga.registrationservice.common.enums.Gender;
import org.mounanga.registrationservice.common.event.owner.OwnerCreatedEvent;
import org.mounanga.registrationservice.common.event.owner.OwnerDeletedEvent;
import org.mounanga.registrationservice.common.event.owner.OwnerUpdatedEvent;
import org.mounanga.registrationservice.queries.entity.Owner;
import org.mounanga.registrationservice.queries.repository.OwnerRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class OwnerEventHandlerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerEventHandlerService ownerEventHandlerService;

    @BeforeEach
    void setUp() {
        ownerEventHandlerService = new OwnerEventHandlerService(ownerRepository);
    }

    @Test
    void onOwnerCreatedEvent() {
        OwnerCreatedEvent event = new OwnerCreatedEvent(
                "id",
                "John",
                "Doe",
                LocalDate.of(1994,1,22),
                "World",
                Gender.M,
                "World",
                "CIN"
        );
        Owner owner = Owner.builder()
                .id(event.getId())
                .firstname(event.getFirstname())
                .lastname(event.getLastname())
                .dateOfBirth(event.getDateOfBirth())
                .placeOfBirth(event.getPlaceOfBirth())
                .gender(event.getGender())
                .cin(event.getCin())
                .nationality(event.getNationality())
                .build();
        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);

        Owner response = ownerEventHandlerService.on(event);
        assertNotNull(response);
        assertEquals(event.getId(), response.getId());
        assertEquals(event.getFirstname(), response.getFirstname());
        assertEquals(event.getLastname(), response.getLastname());
        assertEquals(event.getDateOfBirth(), response.getDateOfBirth());
        assertEquals(event.getPlaceOfBirth(), response.getPlaceOfBirth());
        assertEquals(event.getGender(), response.getGender());
        assertEquals(event.getCin(), response.getCin());
        assertEquals(event.getNationality(), response.getNationality());
        verify(ownerRepository, times(1)).save(any(Owner.class));
    }

    @Test
    void onOwnerUpdatedEvent() {
        OwnerUpdatedEvent event = new OwnerUpdatedEvent(
                "id",
                "John",
                "Doe",
                LocalDate.of(1994,1,22),
                "World",
                Gender.M,
                "World",
                "CIN"
        );
        Owner owner = Owner.builder()
                .id("id")
                .cin("123456")
                .firstname("Kim")
                .lastname("Bob")
                .dateOfBirth(LocalDate.of(1994,1,22))
                .placeOfBirth(event.getPlaceOfBirth())
                .gender(Gender.M)
                .build();
        Owner updatedOwner = Owner.builder()
                .id(event.getId())
                .firstname(event.getFirstname())
                .lastname(event.getLastname())
                .dateOfBirth(event.getDateOfBirth())
                .placeOfBirth(event.getPlaceOfBirth())
                .gender(event.getGender())
                .cin(event.getCin())
                .nationality(event.getNationality())
                .build();

        when(ownerRepository.findById(anyString())).thenReturn(Optional.of(owner));
        when(ownerRepository.save(any(Owner.class))).thenReturn(updatedOwner);
        Owner response = ownerEventHandlerService.on(event);
        assertNotNull(response);
        assertEquals(event.getId(), response.getId());
        assertEquals(event.getFirstname(), response.getFirstname());
        assertEquals(event.getLastname(), response.getLastname());
        assertEquals(event.getDateOfBirth(), response.getDateOfBirth());
        assertEquals(event.getPlaceOfBirth(), response.getPlaceOfBirth());
        assertEquals(event.getGender(), response.getGender());
        assertEquals(event.getCin(), response.getCin());
        assertEquals(event.getNationality(), response.getNationality());
        verify(ownerRepository, times(1)).findById(anyString());
        verify(ownerRepository, times(1)).save(any(Owner.class));
    }

    @Test
    void onOwnerDeletedEvent() {
        OwnerDeletedEvent event = new OwnerDeletedEvent("id");
        doNothing().when(ownerRepository).deleteById(anyString());
        ownerEventHandlerService.on(event);
        verify(ownerRepository, times(1)).deleteById(anyString());
    }

}