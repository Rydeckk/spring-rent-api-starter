package fr.esgi.velib.mapper;

import fr.esgi.velib.domain.CoordonneesGeoEntity;
import fr.esgi.velib.dto.CoordonneesGeoDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CoordonneesGeoMapperTest {

    @Test
    void toDTO_shouldReturnDTOWithMatchingFields() {

        CoordonneesGeoEntity entity = new CoordonneesGeoEntity();
        entity.setId(1);
        entity.setLat(48.8566);
        entity.setLon(2.3522);


        CoordonneesGeoDTO dto = CoordonneesGeoMapper.toDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getLat(), dto.getLat());
        assertEquals(entity.getLon(), dto.getLon());
    }

    @Test
    void toDTO_shouldReturnNullWhenEntityIsNull() {

        CoordonneesGeoDTO dto = CoordonneesGeoMapper.toDTO(null);

        assertNull(dto);
    }
}