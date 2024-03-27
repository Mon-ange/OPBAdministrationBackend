package ca.openbox.process.entities;

import ca.openbox.process.dataobject.LeaveApplicationDO;
import lombok.Data;

import java.util.Date;
@Data
public class LeaveApplication {
    private Integer id;
    private String username;
    private Date dateOfApplication;
    private String handler;
    private String status;
    public LeaveApplication fromDO(LeaveApplicationDO leaveApplicationDO){
        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.id = leaveApplicationDO.getId();
        leaveApplication.username = leaveApplicationDO.getUsername();
        leaveApplication.dateOfApplication = leaveApplicationDO.getDateOfApplication();
        leaveApplication.handler = leaveApplicationDO.getHandler();
        leaveApplication.status = leaveApplicationDO.getStatus();
        return leaveApplication;
    }

}
