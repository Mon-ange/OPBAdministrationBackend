package ca.openbox.user.dto;

import lombok.Data;

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
    private Date birthdate;
    private String JSessionID;
    private String legalname;
    private String address;
    //private String sinno;
    //ban the meaningless transmission of sin
    private String phoneNumber;
    private String email;
    //And mustn't need personaldocuments.

}
