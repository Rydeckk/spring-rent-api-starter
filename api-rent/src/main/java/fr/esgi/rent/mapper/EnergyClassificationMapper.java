package fr.esgi.rent.mapper;

import fr.esgi.rent.domain.EnergyClassificationEntity;
import fr.esgi.rent.dto.EnergyClassificationDTO;
import fr.esgi.rent.dto.PropertyTypeDTO;
import org.springframework.stereotype.Component;

@Component
public class EnergyClassificationMapper {

    public static EnergyClassificationDTO toDTO(EnergyClassificationEntity entity) {
        if (entity == null) {
            return null;
        }
        EnergyClassificationDTO dto = new EnergyClassificationDTO();
        dto.setId(entity.getId());
        dto.setDesignation(entity.getDesignation());
        return dto;
    }

    public static EnergyClassificationEntity fromDTO(EnergyClassificationDTO dto) {
        if (dto == null) {
            return null;
        }
        EnergyClassificationEntity entity = new EnergyClassificationEntity();
        entity.setId(dto.getId());
        entity.setDesignation(dto.getDesignation());
        return entity;
    }
}
