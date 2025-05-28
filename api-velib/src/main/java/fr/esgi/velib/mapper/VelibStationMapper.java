package fr.esgi.velib.mapper;

import fr.esgi.velib.domain.VelibStationEntity;
import fr.esgi.velib.dto.CoordonneesGeoDTO;
import fr.esgi.velib.dto.VelibStationDTO;
import org.springframework.stereotype.Component;

@Component
public class VelibStationMapper {
    public VelibStationDTO toDTO(VelibStationEntity entity) {
        if (entity == null) return null;

        VelibStationDTO dto = new VelibStationDTO();
        dto.setId(entity.getId());
        dto.setStationcode(entity.getStationcode());
        dto.setName(entity.getName());
        dto.setIs_installed(entity.getIs_installed());
        dto.setCapacity(entity.getCapacity());
        dto.setNumdocksavailable(entity.getNumdocksavailable());
        dto.setNumbikesavailable(entity.getNumbikesavailable());
        dto.setMechanical(entity.getMechanical());
        dto.setEbike(entity.getEbike());
        dto.setIs_renting(entity.getIs_renting());
        dto.setIs_returning(entity.getIs_returning());
        dto.setDuedate(entity.getDuedate());
        dto.setCoordonnees_geo(CoordonneesGeoMapper.toDTO(entity.getCoordonnees_geo()));
        dto.setNom_commune(entity.getNom_commune());
        dto.setCode_insee_commune(entity.getCode_insee_commune());

        return dto;
    }
}
