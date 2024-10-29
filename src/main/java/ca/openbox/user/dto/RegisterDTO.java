package ca.openbox.user.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterDTO {
    private String username;
    private String name;
    private String password;
    private Date birthdate;
    //private String roles;
    private String legalname;
    private String sinno;
    private String address;
    private String phoneNumber;
    private String email;
    //record the path
    private String personalDocumentsPath;
}
