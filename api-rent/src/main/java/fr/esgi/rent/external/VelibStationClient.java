package fr.esgi.rent.external;

import fr.esgi.rent.external.model.VelibStationDTO;
import fr.esgi.rent.external.model.VelibStationRequestDTO;
import fr.esgi.rent.external.model.VelibStationResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VelibStationClient {
    private final RestTemplate restTemplate;
    private static final String VELIB_API_URL = "http://localhost:8081/stations/velibs";

    public VelibStationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<String> getCommunesWithStations(List<String> towns) {
        VelibStationRequestDTO request = new VelibStationRequestDTO();
        request.setTowns(towns);

        VelibStationResponseDTO response = restTemplate.postForObject(
                VELIB_API_URL, request, VelibStationResponseDTO.class);

        if (response == null || response.getStations() == null) return List.of();

        return response.getStations().stream()
                .map(VelibStationDTO::getNom_commune)
                .distinct()
                .collect(Collectors.toList());
    }
}