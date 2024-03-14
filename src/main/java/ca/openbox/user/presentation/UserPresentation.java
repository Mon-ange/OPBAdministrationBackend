package ca.openbox.user.presentation;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



@Data
@Entity
@Table(name = "opb_user")
public class UserPresentation {
    @Id
    private String username;
    private String name;
    private String roles;

}
