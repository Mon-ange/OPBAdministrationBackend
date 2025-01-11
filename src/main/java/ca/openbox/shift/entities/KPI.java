package ca.openbox.shift.entities;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class KPI {
    private Integer target;
    private Integer bonus;
    ZonedDateTime startDateTime;
    ZonedDateTime endDateTime;
}
