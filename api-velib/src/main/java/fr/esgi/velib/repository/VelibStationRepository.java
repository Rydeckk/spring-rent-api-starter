package fr.esgi.velib.repository;
import fr.esgi.velib.domain.VelibStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VelibStationRepository extends
        JpaRepository<VelibStationEntity, UUID> {

}
