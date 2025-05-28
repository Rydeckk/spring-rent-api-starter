package fr.esgi.rent.api;

import fr.esgi.rent.dto.RentalPropertyDTO;
import fr.esgi.rent.dto.RentalPropertyFilterRequestDTO;
import fr.esgi.rent.external.VelibStationClient;
import fr.esgi.rent.mapper.RentalPropertyMapper;
import fr.esgi.rent.repository.RentalPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class RentalPropertyResource {
    private final RentalPropertyRepository rentalPropertyRepository;
    private final RentalPropertyMapper rentalPropertyMapper;
    private final VelibStationClient velibStationClient;

    @Autowired
    public RentalPropertyResource(RentalPropertyRepository rentalPropertyRepository, RentalPropertyMapper rentalPropertyMapper, VelibStationClient velibStationClient) {
        this.rentalPropertyRepository = rentalPropertyRepository;
        this.rentalPropertyMapper = rentalPropertyMapper;
        this.velibStationClient = velibStationClient;
    }

    @GetMapping("/rental-properties")
    public ResponseEntity<List<RentalPropertyDTO>> getRentalProperties(
            @RequestParam(name = "near_velib_stations", required = false, defaultValue = "false") boolean nearVelibStations
    ) {
        List<RentalPropertyDTO> allProperties = rentalPropertyRepository.findAll()
                .stream()
                .map(rentalPropertyMapper::toDTO)
                .toList();

        if (!nearVelibStations) {
            return ResponseEntity.ok(allProperties);
        }

        List<String> towns = allProperties.stream()
                .map(RentalPropertyDTO::getTown)
                .distinct()
                .toList();

        List<String> townsWithStations = velibStationClient.getCommunesWithStations(towns);

        List<RentalPropertyDTO> filtered = allProperties.stream()
                .filter(prop -> townsWithStations.contains(prop.getTown()))
                .toList();

        return ResponseEntity.ok(filtered);
    }

    @GetMapping("/rental-properties/{id}")
    public ResponseEntity<RentalPropertyDTO> getRentalPropertyById(@PathVariable UUID id) {
        return rentalPropertyRepository.findById(id)
                .map(rentalPropertyMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/rental-properties")
    public ResponseEntity<RentalPropertyDTO> createRentalProperty(@RequestBody RentalPropertyDTO dto) {
        var entity = rentalPropertyMapper.fromDTO(dto);
        var saved = rentalPropertyRepository.save(entity);
        return ResponseEntity.ok(rentalPropertyMapper.toDTO(saved));
    }


}
