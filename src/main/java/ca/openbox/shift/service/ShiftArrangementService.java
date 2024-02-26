package ca.openbox.shift.service;

import ca.openbox.shift.entities.ShiftArrangement;
import ca.openbox.shift.repository.ShiftArrangementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftArrangementService {
    @Autowired
    ShiftArrangementRepository shiftArrangementRepository;

    public void addArrangement(ShiftArrangement shiftArrangement){
        shiftArrangementRepository.save(shiftArrangement.getDO());
    }
}
