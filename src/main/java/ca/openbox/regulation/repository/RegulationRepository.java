package ca.openbox.regulation.repository;

import ca.openbox.regulation.dataobject.RegulationDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegulationRepository extends JpaRepository<RegulationDO, Integer> {
}
