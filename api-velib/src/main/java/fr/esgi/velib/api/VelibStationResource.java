package fr.esgi.velib.api;

import fr.esgi.velib.dto.VelibStationRequestDTO;
import fr.esgi.velib.dto.VelibStationResponseDTO;
import fr.esgi.velib.mapper.VelibStationMapper;
import fr.esgi.velib.repository.VelibStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations/velibs")
public class VelibStationResource {
    private final VelibStationRepository velibStationRepository;
    private final VelibStationMapper velibStationMapper;

    @Autowired
    public VelibStationResource(VelibStationRepository velibStationRepository, VelibStationMapper velibStationMapper) {
        this.velibStationRepository = velibStationRepository;
        this.velibStationMapper = velibStationMapper;
    }

    @PostMapping
    public ResponseEntity<VelibStationResponseDTO> getStationsByTowns(@RequestBody VelibStationRequestDTO request) {
        List<String> towns = request.getTowns();

        var stations = velibStationRepository.findAll().stream()
                .filter(station -> towns.contains(station.getNom_commune()))
                .map(velibStationMapper::toDTO)
                .toList();

        return ResponseEntity.ok(new VelibStationResponseDTO(stations));
    }

}
