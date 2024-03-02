package ca.openbox.shift.dataobject;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "opb_shift_arrangement")
public class ShiftArrangementDO {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer username;
    private ZonedDateTime start;
    private ZonedDateTime end;
}

