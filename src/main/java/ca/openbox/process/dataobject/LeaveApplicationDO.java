package ca.openbox.process.dataobject;

import lombok.Data;

import java.util.Date;
@Data
public class LeaveApplicationDO {
    private Integer id;
    private String username;
    private Date dateOfApplication;
    private String handler;
    private String status;
}
