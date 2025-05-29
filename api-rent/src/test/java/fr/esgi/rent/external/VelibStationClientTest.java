package fr.esgi.rent.external;

import fr.esgi.rent.external.model.VelibStationDTO;
import fr.esgi.rent.external.model.VelibStationResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class VelibStationClientTest {

    private RestTemplate restTemplate;
    private VelibStationClient velibStationClient;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        velibStationClient = new VelibStationClient(restTemplate);
    }

    @Test
    void getCommunesWithStations_shouldReturnDistinctCommunes() {
        // Arrange
        List<String> towns = List.of("Paris", "Lyon");

        VelibStationDTO station1 = new VelibStationDTO();
        station1.setNom_commune("Paris");

        VelibStationDTO station2 = new VelibStationDTO();
        station2.setNom_commune("Lyon");

        VelibStationDTO station3 = new VelibStationDTO();
        station3.setNom_commune("Paris");

        VelibStationResponseDTO response = new VelibStationResponseDTO();
        response.setStations(List.of(station1, station2, station3));

        when(restTemplate.postForObject(anyString(), any(), eq(VelibStationResponseDTO.class)))
                .thenReturn(response);

        // Act
        List<String> communes = velibStationClient.getCommunesWithStations(towns);

        // Assert
        assertThat(communes).containsExactlyInAnyOrder("Paris", "Lyon");

        verify(restTemplate).postForObject(
                eq("http://localhost:8081/stations/velibs"),
                any(),
                eq(VelibStationResponseDTO.class)
        );
    }

    @Test
    void getCommunesWithStations_shouldReturnEmptyListWhenResponseIsNull() {
        when(restTemplate.postForObject(anyString(), any(), eq(VelibStationResponseDTO.class)))
                .thenReturn(null);

        List<String> communes = velibStationClient.getCommunesWithStations(List.of("Paris"));

        assertThat(communes).isEmpty();
    }

    @Test
    void getCommunesWithStations_shouldReturnEmptyListWhenStationsIsNull() {
        VelibStationResponseDTO response = new VelibStationResponseDTO();
        response.setStations(null);

        when(restTemplate.postForObject(anyString(), any(), eq(VelibStationResponseDTO.class)))
                .thenReturn(response);

        List<String> communes = velibStationClient.getCommunesWithStations(List.of("Paris"));

        assertThat(communes).isEmpty();
    }
}