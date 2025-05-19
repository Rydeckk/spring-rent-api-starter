package fr.esgi.rent.api;

import fr.esgi.rent.dto.RentalPropertyDTO;
import fr.esgi.rent.mapper.RentalPropertyMapper;
import fr.esgi.rent.repository.RentalPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
