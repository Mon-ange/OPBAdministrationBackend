package ca.openbox.shift.entities;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Sprint {
    ZonedDateTime startTime;
    ZonedDateTime endTime;
}
