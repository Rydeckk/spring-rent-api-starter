package fr.esgi.rent.mapper;

import fr.esgi.rent.domain.RentalPropertyEntity;
import fr.esgi.rent.dto.*;
import org.springframework.stereotype.Component;

@Component
public class RentalPropertyMapper {

    public RentalPropertyDTO toDTO(RentalPropertyEntity entity) {
        if (entity == null) return null;

        RentalPropertyDTO dto = new RentalPropertyDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setTown(entity.getTown());
        dto.setAddress(entity.getAddress());
        dto.setPropertyType(PropertyTypeMapper.toDTO(entity.getPropertyType()));
        dto.setRentAmount(entity.getRentAmount());
        dto.setSecurityDepositAmount(entity.getSecurityDepositAmount());
        dto.setArea(entity.getArea());
        dto.setNumberOfBedrooms(entity.getNumberOfBedrooms());
        dto.setFloorNumber(entity.getFloorNumber());
        dto.setNumberOfFloors(entity.getNumberOfFloors());
        dto.setConstructionYear(entity.getConstructionYear());
        dto.setEnergyClassification(EnergyClassificationMapper.toDTO(entity.getEnergyClassification()));
        dto.setHasElevator(entity.isHasElevator());
        dto.setHasIntercom(entity.isHasIntercom());
        dto.setHasBalcony(entity.isHasBalcony());
        dto.setHasParkingSpace(entity.isHasParkingSpace());

        return dto;
    }

    public RentalPropertyEntity fromDTO(RentalPropertyDTO dto) {
        if (dto == null) return null;

        RentalPropertyEntity entity = new RentalPropertyEntity();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setTown(dto.getTown());
        entity.setAddress(dto.getAddress());
        entity.setPropertyType(PropertyTypeMapper.fromDTO(dto.getPropertyType()));
        entity.setRentAmount(dto.getRentAmount());
        entity.setSecurityDepositAmount(dto.getSecurityDepositAmount());
        entity.setArea(dto.getArea());
        entity.setNumberOfBedrooms(dto.getNumberOfBedrooms());
        entity.setFloorNumber(dto.getFloorNumber());
        entity.setNumberOfFloors(dto.getNumberOfFloors());
        entity.setConstructionYear(dto.getConstructionYear());
        entity.setEnergyClassification(EnergyClassificationMapper.fromDTO(dto.getEnergyClassification()));
        entity.setHasElevator(dto.isHasElevator());
        entity.setHasIntercom(dto.isHasIntercom());
        entity.setHasBalcony(dto.isHasBalcony());
        entity.setHasParkingSpace(dto.isHasParkingSpace());

        return entity;
    }
}