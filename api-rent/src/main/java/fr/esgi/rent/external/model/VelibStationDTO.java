package fr.esgi.rent.external.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VelibStationDTO {
    private int id;
    private String stationcode;
    private String name;
    private String is_installed;
    private int capacity;
    private int numdocksavailable;
    private int numbikesavailable;
    private int mechanical;
    private int ebike;
    private String is_renting;
    private String is_returning;
    private String duedate;
    private CoordonneesGeoDTO coordonnees_geo;
    private String nom_commune;
    private String code_insee_commune;
}