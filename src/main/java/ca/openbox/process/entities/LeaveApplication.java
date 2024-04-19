package ca.openbox.process.entities;

import ca.openbox.process.dataobject.LeaveApplicationDO;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;
@Data
public class LeaveApplication {
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
    private String note;
    static public LeaveApplication fromDO(LeaveApplicationDO leaveApplicationDO){
        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.id = leaveApplicationDO.getId();
        leaveApplication.applicant = leaveApplicationDO.getApplicant();
        leaveApplication.currentHandler = leaveApplicationDO.getCurrentHandler();
        leaveApplication.status = leaveApplicationDO.getStatus();
        leaveApplication.leaveType = leaveApplicationDO.getLeaveType();
        leaveApplication.start = leaveApplicationDO.getStart();
        leaveApplication.end= leaveApplicationDO.getEnd();
        leaveApplication.submitTime = leaveApplicationDO.getSubmitTime();
        leaveApplication.rejectReason = leaveApplicationDO.getRejectReason();
        leaveApplication.reason=leaveApplicationDO.getReason();
        leaveApplication.note = leaveApplicationDO.getNote();
        return leaveApplication;
    }
    public LeaveApplicationDO toDO(){
        LeaveApplicationDO leaveApplicationDO = new LeaveApplicationDO();
        leaveApplicationDO.setId(id);
        leaveApplicationDO.setSubmitTime(submitTime);
        leaveApplicationDO.setApplicant(applicant);
        leaveApplicationDO.setStatus(status);
        leaveApplicationDO.setLeaveType(leaveType);
        leaveApplicationDO.setStart(start);
        leaveApplicationDO.setEnd(end);
        leaveApplicationDO.setCurrentHandler(currentHandler);
        leaveApplicationDO.setReason(reason);
        leaveApplicationDO.setRejectReason(rejectReason);
        leaveApplicationDO.setNote(note);
        return leaveApplicationDO;

    }

}
