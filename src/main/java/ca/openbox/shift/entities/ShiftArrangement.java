package ca.openbox.shift.entities;

import ca.openbox.shift.dataobject.ShiftArrangementDO;
import ca.openbox.shift.dto.ShiftArrangementDTO;
import lombok.Data;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
@Data
public class ShiftArrangement {
    private Integer id;
    private Integer username;
    private ZonedDateTime start;
    private ZonedDateTime end;
    public ShiftArrangementDO getDO(){
        ShiftArrangementDO shiftArrangementDO = new ShiftArrangementDO();
        shiftArrangementDO.setId(id);
        shiftArrangementDO.setUsername(username);
        shiftArrangementDO.setStart(start);
        shiftArrangementDO.setEnd(end);
        return shiftArrangementDO;
    }

    static public ShiftArrangement fromDTO(ShiftArrangementDTO shiftArrangementDTO){
        ShiftArrangement shiftArrangement = new ShiftArrangement();
        shiftArrangement.id = shiftArrangementDTO.getId();
        shiftArrangement.start = shiftArrangementDTO.getStart().atZone(ZoneId.of("UTC-8"));
        shiftArrangement.end = shiftArrangementDTO.getEnd().atZone(ZoneId.of("UTC-8"));
        shiftArrangement.username = shiftArrangementDTO.getUserId();
        return shiftArrangement;
    }
}
