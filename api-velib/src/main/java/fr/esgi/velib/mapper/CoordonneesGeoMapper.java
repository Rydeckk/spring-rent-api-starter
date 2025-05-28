package fr.esgi.velib.mapper;

import fr.esgi.velib.domain.CoordonneesGeoEntity;
import fr.esgi.velib.dto.CoordonneesGeoDTO;
import org.springframework.stereotype.Component;

@Component
public class CoordonneesGeoMapper {
    public static CoordonneesGeoDTO toDTO(CoordonneesGeoEntity entity) {
        if (entity == null) return null;

        CoordonneesGeoDTO dto = new CoordonneesGeoDTO();
        dto.setId(entity.getId());
        dto.setLon(entity.getLon());
        dto.setLat(entity.getLat());

        return dto;
    }
}
