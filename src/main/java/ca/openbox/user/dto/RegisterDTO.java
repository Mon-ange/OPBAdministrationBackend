package ca.openbox.user.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class RegisterDTO {
    private String username;
    private String name;
    private String password;
    private LocalDate birthdate;
    //private String roles;
    private String legalname;
    private String sinno;
    private String address;
    private String phoneNumber;
    private String email;
    //record the path
    private String personalDocumentsPath;
}
