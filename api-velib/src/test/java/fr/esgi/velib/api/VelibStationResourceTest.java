package fr.esgi.velib.api;

import fr.esgi.velib.domain.VelibStationEntity;
import fr.esgi.velib.dto.VelibStationDTO;
import fr.esgi.velib.dto.VelibStationRequestDTO;
import fr.esgi.velib.dto.VelibStationResponseDTO;
import fr.esgi.velib.mapper.VelibStationMapper;
import fr.esgi.velib.repository.VelibStationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VelibStationResourceTest {

    @Mock
    private VelibStationRepository repository;

    @Mock
    private VelibStationMapper mapper;

    @InjectMocks
    private VelibStationResource resource;

    private VelibStationEntity stationEntity;
    private VelibStationDTO stationDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        stationEntity = new VelibStationEntity();
        stationEntity.setNom_commune("Nogent-sur-Marne");

        stationDTO = new VelibStationDTO();
        stationDTO.setNom_commune("Nogent-sur-Marne");
    }

    @Test
    void shouldReturnStationsMatchingRequestedTowns() {
        // Given
        VelibStationRequestDTO request = new VelibStationRequestDTO();
        request.setTowns(List.of("Nogent-sur-Marne"));

        when(repository.findAll()).thenReturn(List.of(stationEntity));
        when(mapper.toDTO(stationEntity)).thenReturn(stationDTO);

        // When
        ResponseEntity<VelibStationResponseDTO> response = resource.getStationsByTowns(request);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        List<VelibStationDTO> stations = response.getBody().getStations();
        assertEquals(1, stations.size());
        assertEquals("Nogent-sur-Marne", stations.get(0).getNom_commune());

        verify(repository).findAll();
        verify(mapper).toDTO(stationEntity);
    }

    @Test
    void shouldReturnEmptyListIfNoStationsMatch() {
        // Given
        VelibStationRequestDTO request = new VelibStationRequestDTO();
        request.setTowns(List.of("Vincennes"));

        when(repository.findAll()).thenReturn(List.of(stationEntity)); // station in Nogent

        // When
        ResponseEntity<VelibStationResponseDTO> response = resource.getStationsByTowns(request);

        // Then
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().getStations().isEmpty());

        verify(repository).findAll();
        verify(mapper, never()).toDTO(any());
    }
}