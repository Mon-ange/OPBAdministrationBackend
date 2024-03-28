package ca.openbox.statistics.presentation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class WorkTimeStatistic {
    private String username;
    private String userRealName;
    private double hours;
    private long minutes;
}
