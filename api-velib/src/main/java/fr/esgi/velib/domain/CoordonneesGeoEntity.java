package fr.esgi.velib.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "coordonnee_geo")
public class CoordonneesGeoEntity {
    @GeneratedValue
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "lon")
    private double lon;

    @Column(name = "lat")
    private double lat;
}
