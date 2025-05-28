package fr.esgi.rent.external.model;

import java.util.List;

public class VelibStationResponseDTO {
    private List<VelibStationDTO> stations;

    public VelibStationResponseDTO() {}

    public List<VelibStationDTO> getStations() {
        return stations;
    }

    public void setStations(List<VelibStationDTO> stations) {
        this.stations = stations;
    }
}