package ca.openbox.process.dto;

import lombok.Data;

import java.time.ZonedDateTime;
@Data
public class PutLeaveApplicationDTO {
    private String applicant;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private String leaveType;
    private String reason;
}
