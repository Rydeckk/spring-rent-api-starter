package fr.esgi.rent.mapper;

import fr.esgi.rent.domain.PropertyTypeEntity;
import fr.esgi.rent.dto.PropertyTypeDTO;
import org.springframework.stereotype.Component;

@Component
public class PropertyTypeMapper {

        public static PropertyTypeDTO toDTO(PropertyTypeEntity entity) {
                if (entity == null) {
                        return null;
                }
                PropertyTypeDTO dto = new PropertyTypeDTO();
                dto.setId(entity.getId());
                dto.setDesignation(entity.getDesignation());
                return dto;
        }

        public static PropertyTypeEntity fromDTO(PropertyTypeDTO dto) {
                if (dto == null) {
                        return null;
                }
                PropertyTypeEntity entity = new PropertyTypeEntity();
                entity.setId(dto.getId());
                entity.setDesignation(dto.getDesignation());
                return entity;
        }
}