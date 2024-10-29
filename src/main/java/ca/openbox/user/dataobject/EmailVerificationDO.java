package ca.openbox.user.dataobject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "opb_email_verification")
public class EmailVerificationDO {
    @Id
    private String email;
    private String verificationCode;
}
