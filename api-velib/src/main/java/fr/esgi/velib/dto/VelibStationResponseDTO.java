package fr.esgi.velib.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class VelibStationResponseDTO {
    private List<VelibStationDTO> stations;

    public VelibStationResponseDTO() {}

    public VelibStationResponseDTO(List<VelibStationDTO> stations) {
        this.stations = stations;
    }

}
