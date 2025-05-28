package fr.esgi.velib.api.mapper;

import fr.esgi.velib.domain.CoordonneesGeoEntity;
import fr.esgi.velib.domain.VelibStationEntity;
import fr.esgi.velib.mapper.VelibStationMapper;
import fr.esgi.velib.dto.VelibStationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class VelibStationMapperTest {

    private VelibStationMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new VelibStationMapper();
    }

    @Test
    void toDTO_shouldReturnDTOWithMatchingFields() {
        CoordonneesGeoEntity geo = new CoordonneesGeoEntity();
        geo.setLat(48.8566);
        geo.setLon(2.3522);

        VelibStationEntity entity = new VelibStationEntity();
        entity.setId(1);
        entity.setStationcode("41301");
        entity.setName("Bois de Vincennes - Gare");
        entity.setIs_installed("OUI");
        entity.setCapacity(20);
        entity.setNumdocksavailable(5);
        entity.setNumbikesavailable(15);
        entity.setMechanical(10);
        entity.setEbike(5);
        entity.setIs_renting("OUI");
        entity.setIs_returning("OUI");
        entity.setDuedate(String.valueOf(ZonedDateTime.now()));
        entity.setCoordonnees_geo(geo);
        entity.setNom_commune("Vincennes");
        entity.setCode_insee_commune("94300");

        // When
        VelibStationDTO dto = mapper.toDTO(entity);

        // Then
        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals("41301", dto.getStationcode());
        assertEquals("Bois de Vincennes - Gare", dto.getName());
        assertEquals("OUI", dto.getIs_installed());
        assertEquals(20, dto.getCapacity());
        assertEquals(5, dto.getNumdocksavailable());
        assertEquals(15, dto.getNumbikesavailable());
        assertEquals(10, dto.getMechanical());
        assertEquals(5, dto.getEbike());
        assertEquals("OUI", dto.getIs_renting());
        assertEquals("OUI", dto.getIs_returning());
        assertEquals(entity.getDuedate(), dto.getDuedate());
        assertNotNull(dto.getCoordonnees_geo());
        assertEquals(48.8566, dto.getCoordonnees_geo().getLat());
        assertEquals(2.3522, dto.getCoordonnees_geo().getLon());
        assertEquals("Vincennes", dto.getNom_commune());
        assertEquals("94300", dto.getCode_insee_commune());
    }

    @Test
    void toDTO_shouldReturnNullWhenEntityIsNull() {
        VelibStationDTO dto = mapper.toDTO(null);


        assertNull(dto);
    }
}