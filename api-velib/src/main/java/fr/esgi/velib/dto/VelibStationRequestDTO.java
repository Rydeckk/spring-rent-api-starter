package fr.esgi.velib.dto;

import java.util.List;

public class VelibStationRequestDTO {
    private List<String> towns;

    public List<String> getTowns() {
        return towns;
    }

    public void setTowns(List<String> towns) {
        this.towns = towns;
    }
}
