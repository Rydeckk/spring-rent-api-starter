package fr.esgi.rent.api.mapper;

import fr.esgi.rent.api.dto.EnergyClassificationDTO;
import fr.esgi.rent.api.dto.PropertyTypeDTO;
import fr.esgi.rent.api.dto.RentalPropertyDTO;
import fr.esgi.rent.domain.EnergyClassificationEntity;
import fr.esgi.rent.domain.PropertyTypeEntity;
import fr.esgi.rent.domain.RentalPropertyEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RentalPropertyMapperTest {

    @InjectMocks
    private RentalPropertyMapper rentalPropertyMapper;

    @Test
    void toDto_withCompleteEntity_shouldReturnCompleteMappedDto() {
        // Given
        UUID id = UUID.randomUUID();
        UUID propertyTypeId = UUID.randomUUID();
        UUID energyClassificationId = UUID.randomUUID();

        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity();
        propertyTypeEntity.setId(propertyTypeId);
        propertyTypeEntity.setDesignation("Apartment");

        EnergyClassificationEntity energyClassificationEntity = new EnergyClassificationEntity();
        energyClassificationEntity.setId(energyClassificationId);
        energyClassificationEntity.setDesignation("B");

        RentalPropertyEntity entity = new RentalPropertyEntity();
        entity.setId(id);
        entity.setDescription("Nice apartment");
        entity.setTown("Paris");
        entity.setAddress("123 Rue de Paris");
        entity.setPropertyType(propertyTypeEntity);
        entity.setRentAmount(950.0);
        entity.setSecurityDepositAmount(1900.0);
        entity.setArea(75.5);
        entity.setNumberOfBedrooms((byte) 2);
        entity.setFloorNumber((short) 3);
        entity.setNumberOfFloors((short) 6);
        entity.setConstructionYear((short) 2010);
        entity.setEnergyClassification(energyClassificationEntity);
        entity.setHasElevator(true);
        entity.setHasIntercom(true);
        entity.setHasBalcony(false);
        entity.setHasParkingSpace(true);

        // When
        RentalPropertyDTO result = rentalPropertyMapper.toDto(entity);

        // Then
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Nice apartment", result.getDescription());
        assertEquals("Paris", result.getTown());
        assertEquals("123 Rue de Paris", result.getAddress());
        assertNotNull(result.getPropertyType());
        assertEquals(propertyTypeId, result.getPropertyType().getId());
        assertEquals("Apartment", result.getPropertyType().getDesignation());
        assertEquals(950.0, result.getRentAmount());
        assertEquals(1900.0, result.getSecurityDepositAmount());
        assertEquals(75.5, result.getArea());
        assertEquals(2, result.getNumberOfBedrooms());
        assertEquals(3, result.getFloorNumber().shortValue());
        assertEquals(6, result.getNumberOfFloors().shortValue());
        assertEquals(2010, result.getConstructionYear());
        assertNotNull(result.getEnergyClassification());
        assertEquals(energyClassificationId, result.getEnergyClassification().getId());
        assertEquals("B", result.getEnergyClassification().getDesignation());
        assertTrue(result.isHasElevator());
        assertTrue(result.isHasIntercom());
        assertFalse(result.isHasBalcony());
        assertTrue(result.isHasParkingSpace());
    }

    @Test
    void toDto_withNullEntity_shouldReturnNull() {
        // When
        RentalPropertyDTO result = rentalPropertyMapper.toDto(null);

        // Then
        assertNull(result);
    }

    @Test
    void toDto_withNullRelations_shouldMapOnlyDirectFields() {
        // Given
        UUID id = UUID.randomUUID();

        RentalPropertyEntity entity = new RentalPropertyEntity();
        entity.setId(id);
        entity.setDescription("Simple apartment");
        entity.setTown("Lyon");
        entity.setPropertyType(null);
        entity.setEnergyClassification(null);

        // When
        RentalPropertyDTO result = rentalPropertyMapper.toDto(entity);

        // Then
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Simple apartment", result.getDescription());
        assertEquals("Lyon", result.getTown());
        assertNull(result.getPropertyType());
        assertNull(result.getEnergyClassification());
    }

    @Test
    void toDtoList_shouldMapAllEntities() {
        // Given
        RentalPropertyEntity entity1 = new RentalPropertyEntity();
        entity1.setId(UUID.randomUUID());
        entity1.setDescription("Property 1");

        RentalPropertyEntity entity2 = new RentalPropertyEntity();
        entity2.setId(UUID.randomUUID());
        entity2.setDescription("Property 2");

        List<RentalPropertyEntity> entities = Arrays.asList(entity1, entity2);

        // When
        List<RentalPropertyDTO> results = rentalPropertyMapper.toDtoList(entities);

        // Then
        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals(entity1.getId(), results.get(0).getId());
        assertEquals("Property 1", results.get(0).getDescription());
        assertEquals(entity2.getId(), results.get(1).getId());
        assertEquals("Property 2", results.get(1).getDescription());
    }

    @Test
    void toEntity_withCompleteDto_shouldReturnCompleteEntity() {
        // Given
        UUID id = UUID.randomUUID();
        UUID propertyTypeId = UUID.randomUUID();
        UUID energyClassificationId = UUID.randomUUID();

        PropertyTypeDTO propertyTypeDTO = new PropertyTypeDTO();
        propertyTypeDTO.setId(propertyTypeId);
        propertyTypeDTO.setDesignation("House");

        EnergyClassificationDTO energyClassificationDTO = new EnergyClassificationDTO();
        energyClassificationDTO.setId(energyClassificationId);
        energyClassificationDTO.setDesignation("A");

        RentalPropertyDTO dto = new RentalPropertyDTO();
        dto.setId(id);
        dto.setDescription("Lovely house");
        dto.setTown("Marseille");
        dto.setAddress("456 Avenue de la Mer");
        dto.setPropertyType(propertyTypeDTO);
        dto.setRentAmount(1200.0);
        dto.setSecurityDepositAmount(2400.0);
        dto.setArea(120.0);
        dto.setNumberOfBedrooms((byte) 4);
        dto.setFloorNumber((short) 0);
        dto.setNumberOfFloors((short) 2);
        dto.setConstructionYear((short) 2005);
        dto.setEnergyClassification(energyClassificationDTO);
        dto.setHasElevator(false);
        dto.setHasIntercom(true);
        dto.setHasBalcony(true);
        dto.setHasParkingSpace(true);

        // When
        RentalPropertyEntity result = rentalPropertyMapper.toEntity(dto);

        // Then
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Lovely house", result.getDescription());
        assertEquals("Marseille", result.getTown());
        assertEquals("456 Avenue de la Mer", result.getAddress());
        assertNotNull(result.getPropertyType());
        assertEquals(propertyTypeId, result.getPropertyType().getId());
        assertEquals("House", result.getPropertyType().getDesignation());
        assertEquals(1200.0, result.getRentAmount());
        assertEquals(2400.0, result.getSecurityDepositAmount());
        assertEquals(120.0, result.getArea());
        assertEquals(4, result.getNumberOfBedrooms());
        assertEquals(0, result.getFloorNumber().shortValue());
        assertEquals(2, result.getNumberOfFloors().shortValue());
        assertEquals(2005, result.getConstructionYear());
        assertNotNull(result.getEnergyClassification());
        assertEquals(energyClassificationId, result.getEnergyClassification().getId());
        assertEquals("A", result.getEnergyClassification().getDesignation());
        assertFalse(result.isHasElevator());
        assertTrue(result.isHasIntercom());
        assertTrue(result.isHasBalcony());
        assertTrue(result.isHasParkingSpace());
    }

    @Test
    void toEntity_withNullDto_shouldReturnNull() {
        // When
        RentalPropertyEntity result = rentalPropertyMapper.toEntity(null);

        // Then
        assertNull(result);
    }

    @Test
    void toEntity_withNullRelations_shouldMapOnlyDirectFields() {
        // Given
        UUID id = UUID.randomUUID();

        RentalPropertyDTO dto = new RentalPropertyDTO();
        dto.setId(id);
        dto.setDescription("Basic house");
        dto.setTown("Nice");
        dto.setPropertyType(null);
        dto.setEnergyClassification(null);

        // When
        RentalPropertyEntity result = rentalPropertyMapper.toEntity(dto);

        // Then
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Basic house", result.getDescription());
        assertEquals("Nice", result.getTown());
        assertNull(result.getPropertyType());
        assertNull(result.getEnergyClassification());
    }
}