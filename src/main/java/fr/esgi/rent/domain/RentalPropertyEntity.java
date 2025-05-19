package fr.esgi.rent.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Table(name = "rental_property")
public class RentalPropertyEntity {
    @GeneratedValue
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "town")
    private String town;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "property_type_id")
    private PropertyTypeEntity propertyType;

    @Column(name = "rent_amount")
    private double rentAmount;

    @Column(name = "security_deposit_amount")
    private double securityDepositAmount;

    @Column(name = "area")
    private double area;

    @Column(name = "number_of_bedrooms")
    private byte numberOfBedrooms;

    @Column(name = "floor_number")
    private Short floorNumber;

    @Column(name = "number_of_floors")
    private Short numberOfFloors;

    @Column(name = "construction_year")
    private short constructionYear;

    @ManyToOne
    @JoinColumn(name = "energy_classification_id")
    private EnergyClassificationEntity energyClassification;

    @Column(name = "has_elevator")
    private boolean hasElevator;

    @Column(name = "has_intercom")
    private boolean hasIntercom;

    @Column(name = "has_balcony")
    private boolean hasBalcony;

    @Column(name = "has_parking_space")
    private boolean hasParkingSpace;
}
