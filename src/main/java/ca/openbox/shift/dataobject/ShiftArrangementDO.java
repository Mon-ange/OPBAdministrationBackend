package ca.openbox.shift.dataobject;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "opb_shift_arrangement")
public class ShiftArrangementDO {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private ZonedDateTime start;
    private ZonedDateTime end;
}
