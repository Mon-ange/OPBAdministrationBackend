package ca.openbox.regulation.service;

import ca.openbox.regulation.dataobject.RegulationDO;
import ca.openbox.regulation.entities.Regulation;
import ca.openbox.regulation.repository.RegulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegulationService {

    @Autowired
    private RegulationRepository regulationRepository;

    public Regulation getRegulationById(Integer id) {
        Optional<RegulationDO> regulationDO = regulationRepository.findById(id);
        return regulationDO.map(Regulation::fromDO)
                .orElseThrow(() -> new RuntimeException("Regulation not found with ID: " + id));
    }

    public Regulation updateRegulation(Regulation regulation) {
        RegulationDO regulationDO = regulation.toDO();
        RegulationDO savedRegulationDO = regulationRepository.save(regulationDO);
        return Regulation.fromDO(savedRegulationDO);
    }
}
