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
    private String username;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private String status;
    public ShiftArrangementDO getDO(){
        ShiftArrangementDO shiftArrangementDO = new ShiftArrangementDO();
        shiftArrangementDO.setId(id);
        shiftArrangementDO.setUsername(username);
        shiftArrangementDO.setStart(start);
        shiftArrangementDO.setEnd(end);
        shiftArrangementDO.setStatus(status);
        return shiftArrangementDO;
    }

    static public ShiftArrangement fromDO(ShiftArrangementDO shiftArrangementDO){
        ShiftArrangement shiftArrangement = new ShiftArrangement();
        shiftArrangement.id = shiftArrangementDO.getId();
        shiftArrangement.username =shiftArrangementDO.getUsername();
        shiftArrangement.start = shiftArrangementDO.getStart();
        shiftArrangement.end = shiftArrangementDO.getEnd();
        shiftArrangement.status = shiftArrangementDO.getStatus();
        return shiftArrangement;
    }

    static public ShiftArrangement fromDTO(ShiftArrangementDTO shiftArrangementDTO){
        ShiftArrangement shiftArrangement = new ShiftArrangement();
        shiftArrangement.id = shiftArrangementDTO.getId();
        shiftArrangement.start = shiftArrangementDTO.getStart();
        shiftArrangement.end = shiftArrangementDTO.getEnd();
        shiftArrangement.username = shiftArrangementDTO.getUsername();
        shiftArrangement.status = shiftArrangementDTO.getStatus();
        return shiftArrangement;
    }
}
