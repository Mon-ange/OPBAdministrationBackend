package ca.openbox.shift.repository;

import ca.openbox.shift.dataobject.ShiftArrangementDO;
import ca.openbox.shift.entities.ShiftArrangement;
import org.springframework.data.repository.Repository;

import java.time.ZonedDateTime;
import java.util.List;

public interface ShiftArrangementRepository extends Repository<ShiftArrangementDO, Integer> {
    ShiftArrangementDO save(ShiftArrangementDO shiftArrangementDO);
    ShiftArrangementDO delete(ShiftArrangementDO shiftArrangementDO);
    List<ShiftArrangementDO> getShiftArrangementDOByUsernameAndStartBetween(String username, ZonedDateTime left, ZonedDateTime right);
}
