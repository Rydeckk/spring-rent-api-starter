package fr.esgi.rent.api;

import fr.esgi.rent.dto.RentalPropertyDTO;
import fr.esgi.rent.external.VelibStationClient;
import fr.esgi.rent.mapper.RentalPropertyMapper;
import fr.esgi.rent.domain.RentalPropertyEntity;
import fr.esgi.rent.repository.RentalPropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RentalPropertyResourceTest {

    @Mock
    private RentalPropertyRepository rentalPropertyRepository;

    @Mock
    private RentalPropertyMapper rentalPropertyMapper;

    @InjectMocks
    private RentalPropertyResource rentalPropertyResource;

    @Mock
    private MockMvc mockMvc;

    @Mock
    private VelibStationClient velibStationClient;

    private RentalPropertyDTO dto1;
    private RentalPropertyDTO dto2;
    private RentalPropertyEntity entity1;
    private RentalPropertyEntity entity2;
    private UUID id1;
    private UUID id2;

    @BeforeEach
    void setUp() {
        id1 = UUID.randomUUID();
        id2 = UUID.randomUUID();

        dto1 = new RentalPropertyDTO();
        dto1.setId(id1);
        dto1.setDescription("Test Property 1");
        dto1.setTown("Paris");

        dto2 = new RentalPropertyDTO();
        dto2.setId(id2);
        dto2.setDescription("Test Property 2");
        dto2.setTown("Lyon");

        entity1 = new RentalPropertyEntity();
        entity1.setId(id1);
        entity1.setDescription("Test Property 1");
        entity1.setTown("Paris");

        entity2 = new RentalPropertyEntity();
        entity2.setId(id2);
        entity2.setDescription("Test Property 2");
        entity2.setTown("Lyon");
    }

    @Test
    void getRentalPropertyById_whenPropertyExists_shouldReturnProperty() {
        // Given
        when(rentalPropertyRepository.findById(id1)).thenReturn(Optional.of(entity1));
        when(rentalPropertyMapper.toDTO(entity1)).thenReturn(dto1);

        // When
        ResponseEntity<RentalPropertyDTO> response = rentalPropertyResource.getRentalPropertyById(id1);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto1, response.getBody());
        verify(rentalPropertyRepository).findById(id1);
        verify(rentalPropertyMapper).toDTO(entity1);
    }

    @Test
    void getRentalPropertyById_whenPropertyDoesNotExist_shouldReturnNotFound() {
        // Given
        when(rentalPropertyRepository.findById(id1)).thenReturn(Optional.empty());

        // When
        ResponseEntity<RentalPropertyDTO> response = rentalPropertyResource.getRentalPropertyById(id1);

        // Then
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(rentalPropertyRepository).findById(id1);
        verify(rentalPropertyMapper, never()).toDTO(any());
    }

    @Test
    void createRentalProperty_shouldCreateAndReturnProperty() {
        // Given
        when(rentalPropertyMapper.fromDTO(dto1)).thenReturn(entity1);
        when(rentalPropertyRepository.save(entity1)).thenReturn(entity1);
        when(rentalPropertyMapper.toDTO(entity1)).thenReturn(dto1);

        // When
        ResponseEntity<RentalPropertyDTO> response = rentalPropertyResource.createRentalProperty(dto1);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto1, response.getBody());
        verify(rentalPropertyMapper).fromDTO(dto1);
        verify(rentalPropertyRepository).save(entity1);
        verify(rentalPropertyMapper).toDTO(entity1);
    }

    @Test
    void shouldReturnOnlyPropertiesNearVelibStations() {
        // Given
        RentalPropertyEntity property = new RentalPropertyEntity();
        property.setId(UUID.randomUUID());
        property.setTown("Vincennes");

        RentalPropertyDTO dto = new RentalPropertyDTO();
        dto.setTown("Vincennes");

        when(rentalPropertyRepository.findAll()).thenReturn(List.of(property));
        when(rentalPropertyMapper.toDTO(property)).thenReturn(dto);
        when(velibStationClient.getCommunesWithStations(List.of("Vincennes"))).thenReturn(List.of("Vincennes"));

        // When
        var response = rentalPropertyResource.getRentalProperties(true);

        // Then
        assertEquals(1, response.getBody().size());
        assertEquals("Vincennes", response.getBody().get(0).getTown());
    }
}