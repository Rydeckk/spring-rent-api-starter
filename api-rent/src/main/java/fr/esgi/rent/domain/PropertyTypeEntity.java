package fr.esgi.rent.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "property_type")
public class PropertyTypeEntity {
    @GeneratedValue
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "designation")
    private String designation;

}
