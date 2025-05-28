package fr.esgi.rent.dto;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
public class RentalPropertyDTO {

    private UUID id;
    private String description;
    private String town;
    private String address;
    private PropertyTypeDTO propertyType;
    private double rentAmount;
    private double securityDepositAmount;
    private double area;
    private byte numberOfBedrooms;
    private Short floorNumber;
    private Short numberOfFloors;
    private short constructionYear;
    private EnergyClassificationDTO energyClassification;
    private boolean hasElevator;
    private boolean hasIntercom;
    private boolean hasBalcony;
    private boolean hasParkingSpace;
}