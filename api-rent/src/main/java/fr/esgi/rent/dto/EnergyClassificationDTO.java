package fr.esgi.rent.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
public class EnergyClassificationDTO {

    private UUID id;
    private String designation;
}