package fr.esgi.rent.api;

import fr.esgi.rent.api.dto.RentalPropertyDTO;
import fr.esgi.rent.api.mapper.RentalPropertyMapper;
import fr.esgi.rent.domain.RentalPropertyEntity;
import fr.esgi.rent.repository.RentalPropertyRepository;
import fr.esgi.rent.service.RentalPropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RentalPropertyResourceTest {

    @Mock
    private RentalPropertyRepository rentalPropertyRepository;

    @Mock
    private RentalPropertyMapper rentalPropertyMapper;

    @Mock
    private RentalPropertyService rentalPropertyService;

    @InjectMocks
    private RentalPropertyResource rentalPropertyResource;

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
    void getAllRentalProperties_shouldReturnAllProperties() {
        // Given
        List<RentalPropertyDTO> expectedDtos = Arrays.asList(dto1, dto2);
        when(rentalPropertyService.getAllRentalProperties()).thenReturn(expectedDtos);

        // When
        List<RentalPropertyDTO> actualDtos = rentalPropertyResource.getAllRentalProperties();

        // Then
        assertEquals(expectedDtos, actualDtos);
        verify(rentalPropertyService).getAllRentalProperties();
    }

    @Test
    void getRentalPropertyById_whenPropertyExists_shouldReturnProperty() {
        // Given
        when(rentalPropertyRepository.findById(id1)).thenReturn(Optional.of(entity1));
        when(rentalPropertyMapper.toDto(entity1)).thenReturn(dto1);

        // When
        ResponseEntity<RentalPropertyDTO> response = rentalPropertyResource.getRentalPropertyById(id1);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto1, response.getBody());
        verify(rentalPropertyRepository).findById(id1);
        verify(rentalPropertyMapper).toDto(entity1);
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
        verify(rentalPropertyMapper, never()).toDto(any());
    }

    @Test
    void createRentalProperty_shouldCreateAndReturnProperty() {
        // Given
        when(rentalPropertyMapper.toEntity(dto1)).thenReturn(entity1);
        when(rentalPropertyRepository.save(entity1)).thenReturn(entity1);
        when(rentalPropertyMapper.toDto(entity1)).thenReturn(dto1);

        // When
        ResponseEntity<RentalPropertyDTO> response = rentalPropertyResource.createRentalProperty(dto1);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto1, response.getBody());
        verify(rentalPropertyMapper).toEntity(dto1);
        verify(rentalPropertyRepository).save(entity1);
        verify(rentalPropertyMapper).toDto(entity1);
    }

    @Test
    void updateRentalProperty_whenPropertyExists_shouldUpdateAndReturnProperty() {
        // Given
        when(rentalPropertyRepository.existsById(id1)).thenReturn(true);
        when(rentalPropertyMapper.toEntity(dto1)).thenReturn(entity1);
        when(rentalPropertyRepository.save(entity1)).thenReturn(entity1);
        when(rentalPropertyMapper.toDto(entity1)).thenReturn(dto1);

        // When
        ResponseEntity<RentalPropertyDTO> response = rentalPropertyResource.updateRentalProperty(id1, dto1);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto1, response.getBody());
        verify(rentalPropertyRepository).existsById(id1);
        verify(rentalPropertyMapper).toEntity(dto1);
        verify(rentalPropertyRepository).save(entity1);
        verify(rentalPropertyMapper).toDto(entity1);
    }

    @Test
    void updateRentalProperty_whenPropertyDoesNotExist_shouldReturnNotFound() {
        // Given
        when(rentalPropertyRepository.existsById(id1)).thenReturn(false);

        // When
        ResponseEntity<RentalPropertyDTO> response = rentalPropertyResource.updateRentalProperty(id1, dto1);

        // Then
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(rentalPropertyRepository).existsById(id1);
        verify(rentalPropertyMapper, never()).toEntity(any());
        verify(rentalPropertyRepository, never()).save(any());
        verify(rentalPropertyMapper, never()).toDto(any());
    }

    @Test
    void deleteRentalProperty_whenPropertyExists_shouldReturnNoContent() {
        // Given
        when(rentalPropertyRepository.existsById(id1)).thenReturn(true);

        // When
        ResponseEntity<Void> response = rentalPropertyResource.deleteRentalProperty(id1);

        // Then
        assertEquals(204, response.getStatusCodeValue());
        verify(rentalPropertyRepository).existsById(id1);
        verify(rentalPropertyRepository).deleteById(id1);
    }

    @Test
    void deleteRentalProperty_whenPropertyDoesNotExist_shouldReturnNotFound() {
        // Given
        when(rentalPropertyRepository.existsById(id1)).thenReturn(false);

        // When
        ResponseEntity<Void> response = rentalPropertyResource.deleteRentalProperty(id1);

        // Then
        assertEquals(404, response.getStatusCodeValue());
        verify(rentalPropertyRepository).existsById(id1);
        verify(rentalPropertyRepository, never()).deleteById(any());
    }
}