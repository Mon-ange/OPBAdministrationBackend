package ca.openbox.shift.dataobject;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
@Entity
@Table(name="opb_employee_prefer_workday")
public class EmployeePreferWorkdayDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private ZonedDateTime preferDate;

}
