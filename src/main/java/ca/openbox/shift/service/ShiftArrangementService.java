package ca.openbox.shift.service;

import ca.openbox.shift.dataobject.ShiftArrangementDO;
import ca.openbox.shift.entities.ShiftArrangement;
import ca.openbox.shift.repository.ShiftArrangementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class ShiftArrangementService {
    @Autowired
    ShiftArrangementRepository shiftArrangementRepository;

    public ShiftArrangement addArrangement(ShiftArrangement shiftArrangement){
        if(shiftArrangementRepository
                .getShiftArrangementDOByUsernameAndStartBetween(
                        shiftArrangement.getUsername(),
                        shiftArrangement.getStart(),shiftArrangement.getEnd()).size()==0) {
            ShiftArrangementDO insertedShiftArrangementDO = shiftArrangementRepository.save(shiftArrangement.getDO());
            return ShiftArrangement.fromDO(insertedShiftArrangementDO);
        }
        else{
            throw new DuplicateKeyException(String.format("There has been a shift exist for %s", shiftArrangement.getUsername()));
        }
    }
    public void deleteArrangement(ShiftArrangement shiftArrangement){
        shiftArrangementRepository.delete(shiftArrangement.getDO());
    }
    public ShiftArrangement modifyArrangement(ShiftArrangement shiftArrangement){
        ShiftArrangementDO modifiedShiftArrangementDO=shiftArrangementRepository.save(shiftArrangement.getDO());
        return ShiftArrangement.fromDO(modifiedShiftArrangementDO);
    }


}
