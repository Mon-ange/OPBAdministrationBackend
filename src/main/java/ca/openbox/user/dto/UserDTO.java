package ca.openbox.user.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * User Login Response & User Query Response
 */
@Data
public class UserDTO {
    private String username;
    private String name;
    //shouldn't have password
    //private String password;
    private String roles;
    private LocalDate birthdate;
    private String JSessionID;
    private String legalname;
    private String address;
    //private String sinno;
    //ban the meaningless transmission of sin
    private String phoneNumber;
    private String email;
    private String token;
    //And mustn't need personaldocuments.
    private Integer active;
    private String groupName;

}
