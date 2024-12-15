package ca.openbox.user.repository;

import ca.openbox.user.dataobject.UserDO;
import ca.openbox.user.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends Repository<UserDO,String> {
    UserDO getUserDOByUsernameAndActiveIsTrue(String username);
    UserDO save(UserDO userDO);
    UserDO getUserDOByEmail(String email);
    @Query("SELECT u.groupName FROM UserDO u WHERE u.username = :username")
    String findGroupNameByUsername(@Param("username") String username);


}
