package ca.openbox.regulation.controller;

import ca.openbox.regulation.dto.PutRegulationDTO;
import ca.openbox.regulation.entities.Regulation;
import ca.openbox.regulation.service.RegulationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Transactional
@RestController
@RequestMapping("/regulation")
public class RegulationController {

    @Autowired
    private RegulationService regulationService;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/{regulationId}")
    public Regulation getRegulationById(@PathVariable("regulationId") Integer regulationId) {
        return regulationService.getRegulationById(regulationId);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PutMapping("/{regulationId}")
    public Regulation updateRegulationById(
            @PathVariable("regulationId") Integer regulationId,
            @RequestBody PutRegulationDTO putRegulationDTO
    ) {
        Regulation regulation = new Regulation();
        regulation.setId(regulationId);
        regulation.setTitle(putRegulationDTO.getTitle());
        regulation.setContent(putRegulationDTO.getContent());
        regulation.setModifiedTime(putRegulationDTO.getModifiedTime());
        return regulationService.updateRegulation(regulation);
    }

}
