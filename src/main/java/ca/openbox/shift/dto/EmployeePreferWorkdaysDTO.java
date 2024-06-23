package ca.openbox.shift.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class EmployeePreferWorkdaysDTO {
    String username;
    Integer currentMonth;
    List<ZonedDateTime> dates;
}
