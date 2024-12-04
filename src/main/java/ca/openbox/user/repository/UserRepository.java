package ca.openbox.user.repository;

import ca.openbox.user.dataobject.UserDO;
import ca.openbox.user.entities.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<UserDO,String> {
    UserDO getUserDOByUsernameAndActiveIsTrue(String username);
    UserDO save(UserDO userDO);
    UserDO getUserDOByEmail(String email);



}
