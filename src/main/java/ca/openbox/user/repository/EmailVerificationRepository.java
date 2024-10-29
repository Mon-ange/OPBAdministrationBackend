package ca.openbox.user.repository;

import ca.openbox.user.dataobject.EmailVerificationDO;
import org.springframework.data.repository.Repository;

public interface EmailVerificationRepository extends Repository<EmailVerificationDO,String> {
    EmailVerificationDO save(EmailVerificationDO emailVerificationDO);
    EmailVerificationDO getEmailVerificationDOByEmail(String email);
}
