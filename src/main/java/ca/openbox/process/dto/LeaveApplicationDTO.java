package ca.openbox.process.dto;

import lombok.Data;

import java.util.Date;
@Data
public class LeaveApplicationDTO {
    private Integer id;
    private String username;
    private Date dateOfApplication;
    private String handler;
    private String status;
}
