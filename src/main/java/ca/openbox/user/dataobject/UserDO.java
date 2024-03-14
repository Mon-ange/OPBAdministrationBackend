package ca.openbox.user.dataobject;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



@Data
@Entity
@Table(name="opb_user")
public class UserDO {
    @Id
    private String username;
    private String name;
    private String password;
    private String roles;
}
