package ca.openbox.shift.controller;

import ca.openbox.shift.dto.ShiftArrangementDTO;
import ca.openbox.shift.entities.ShiftArrangement;
import ca.openbox.shift.service.ShiftArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shift/shiftarrangement")
public class ShiftArrangementController {

    @Autowired
    ShiftArrangementService shiftArrangementService;
    @PutMapping
    public void putArrangement(@RequestBody ShiftArrangementDTO shiftArrangementDTO){
        System.out.println(shiftArrangementDTO.toString());
        ShiftArrangement shiftArrangement = ShiftArrangement.fromDTO(shiftArrangementDTO);
        System.out.println(shiftArrangement.toString());
        shiftArrangementService.addArrangement(shiftArrangement);
    }
}
