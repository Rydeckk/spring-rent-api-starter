package fr.esgi.velib.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "velib_station")
public class VelibStationEntity {
    @GeneratedValue
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "station_code")
    private String stationcode;

    @Column(name = "name")
    private String name;

    @Column(name = "is_installed")
    private String is_installed;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "numdocksavailable")
    private int numdocksavailable;

    @Column(name = "numbikesavailable")
    private int numbikesavailable;

    @Column(name = "mechanical")
    private int mechanical;

    @Column(name = "ebike")
    private int ebike;

    @Column(name = "is_renting")
    private String is_renting;

    @Column(name = "is_returning")
    private String is_returning;

    @Column(name = "duedate")
    private String duedate;

    @ManyToOne
    @JoinColumn(name = "coordonnees_geo")
    private CoordonneesGeoEntity coordonnees_geo;

    @Column(name = "nom_commune")
    private String nom_commune;

    @Column(name = "code_insee_commune")
    private String code_insee_commune;
}
