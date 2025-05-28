package fr.esgi.rent.external.model;

import java.util.List;

public class VelibStationRequestDTO {
    private List<String> towns;

    public VelibStationRequestDTO() {}

    public VelibStationRequestDTO(List<String> towns) {
        this.towns = towns;
    }

    public List<String> getTowns() {
        return towns;
    }

    public void setTowns(List<String> towns) {
        this.towns = towns;
    }
}