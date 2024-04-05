package ca.openbox.process.dataobject;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;
@Data
@Entity
@Table(name="opb_leave_application")
public class LeaveApplicationDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String applicant;
    private String leaveType;
    private ZonedDateTime submitTime;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private String currentHandler;
    private String status;
    private String reason;
    private String rejectReason;
}
