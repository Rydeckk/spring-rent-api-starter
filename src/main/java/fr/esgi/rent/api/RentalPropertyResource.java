package fr.esgi.rent.api;

import fr.esgi.rent.dto.RentalPropertyDTO;
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

    @Autowired
    public RentalPropertyResource(RentalPropertyRepository rentalPropertyRepository, RentalPropertyMapper rentalPropertyMapper) {
        this.rentalPropertyRepository = rentalPropertyRepository;
        this.rentalPropertyMapper = rentalPropertyMapper;
    }

    @GetMapping("/rental-properties")
    public List<RentalPropertyDTO> getRentalProperties() {
        return rentalPropertyRepository.findAll()
                .stream()
                .map(rentalPropertyMapper::toDTO)
                .toList();
    }

    @GetMapping("/rental-properties/{id}")
    public ResponseEntity<RentalPropertyDTO> getRentalPropertyById(@PathVariable UUID id) {
        return rentalPropertyRepository.findById(id)
                .map(rentalPropertyMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @PostMapping("/rental-properties")
    public ResponseEntity<RentalPropertyDTO> createRentalProperty(@RequestBody RentalPropertyDTO dto) {
        var entity = rentalPropertyMapper.fromDTO(dto);
        var saved = rentalPropertyRepository.save(entity);
        return ResponseEntity.ok(rentalPropertyMapper.toDTO(saved));
    }
}
