package org.mounanga.registrationservice.queries.util;

import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.queries.dto.OwnerResponseDTO;
import org.mounanga.registrationservice.queries.dto.VehicleResponseDTO;
import org.mounanga.registrationservice.queries.entity.Owner;
import org.mounanga.registrationservice.queries.entity.Vehicle;

public class Mappers {

    private Mappers() {
        super();
    }

    public static OwnerResponseDTO fromOwner(@NotNull final Owner owner) {
        return OwnerResponseDTO.builder()
                .id(owner.getId())
                .cin(owner.getCin())
                .firstname(owner.getFirstname())
                .lastname(owner.getLastname())
                .dateOfBirth(owner.getDateOfBirth())
                .placeOfBirth(owner.getPlaceOfBirth())
                .nationality(owner.getNationality())
                .gender(owner.getGender())
                .createdDate(owner.getCreatedDate())
                .createdBy(owner.getCreatedBy())
                .lastModifiedDate(owner.getLastModifiedDate())
                .lastModifiedBy(owner.getLastModifiedBy())
                .build();
    }

    @NotNull
    public static VehicleResponseDTO fromVehicle(@NotNull final Vehicle vehicle) {
        VehicleResponseDTO vehicleResponseDTO = new VehicleResponseDTO();
        vehicleResponseDTO.setId(vehicle.getId());
        vehicleResponseDTO.setMarque(vehicle.getMarque());
        vehicleResponseDTO.setModel(vehicle.getModel());
        vehicleResponseDTO.setDescription(vehicle.getDescription());
        vehicleResponseDTO.setRegistrationId(vehicle.getRegistrationId());
        vehicleResponseDTO.setTaxPower(vehicle.getTaxPower());
        vehicleResponseDTO.setCreateBy(vehicle.getCreateBy());
        vehicleResponseDTO.setCreatedDate(vehicle.getCreatedDate());
        vehicleResponseDTO.setLastModifiedBy(vehicle.getLastModifiedBy());
        vehicleResponseDTO.setLastModifiedDate(vehicle.getLastModifiedDate());
        vehicleResponseDTO.setOwnerId(getOwnerId(vehicle.getOwner()));
        return vehicleResponseDTO;
    }

    private static String getOwnerId(final Owner owner){
        if(owner == null){
            return null;
        }
        return owner.getId();
    }


}
