package fr.esgi.velib.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CoordonneesGeoDTO {
    private int id;
    private double lon;
    private double lat;
}
