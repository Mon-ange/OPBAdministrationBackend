package ca.openbox.shift.repository;

import ca.openbox.shift.dataobject.ShiftArrangementDO;
import org.springframework.data.repository.Repository;

public interface ShiftArrangementRepository extends Repository<ShiftArrangementDO, Integer> {
    ShiftArrangementDO save(ShiftArrangementDO shiftArrangementDO);
}
