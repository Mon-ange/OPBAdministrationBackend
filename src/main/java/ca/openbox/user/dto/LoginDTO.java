package ca.openbox.user.dto;

import lombok.Data;

/**
 * User Login Request
 */
@Data
public class LoginDTO {
    private String username;
    private String password;

}
