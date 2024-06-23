package ca.openbox.shift.entities;

import ca.openbox.shift.dataobject.EmployeePreferWorkdayDO;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class EmployeePreferWorkday {
    private Integer id;
    private String name;
    private ZonedDateTime preferDate;
    public EmployeePreferWorkdayDO getDO(){
        EmployeePreferWorkdayDO employeePreferWorkDateDO = new EmployeePreferWorkdayDO();
        employeePreferWorkDateDO.setId(id);
        employeePreferWorkDateDO.setName(name);
        employeePreferWorkDateDO.setPreferDate(preferDate);
        return employeePreferWorkDateDO;
    }
    static public EmployeePreferWorkday fromDO(EmployeePreferWorkdayDO employeePreferWorkDateDO){
        EmployeePreferWorkday employeePreferWorkDate = new EmployeePreferWorkday();
        employeePreferWorkDate.id = employeePreferWorkDateDO.getId();
        employeePreferWorkDate.name = employeePreferWorkDateDO.getName();
        employeePreferWorkDate.preferDate = employeePreferWorkDateDO.getPreferDate();
        return employeePreferWorkDate;
    }
}
