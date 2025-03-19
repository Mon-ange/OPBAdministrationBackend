package ca.openbox.shift.dataobject;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="opb_statutory_holiday")
public class StatutoryHolidayDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate statutoryDate;
    private String holidayName;
}
