package ca.openbox.user.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String name;
    private String password;
    private String roles;

}
