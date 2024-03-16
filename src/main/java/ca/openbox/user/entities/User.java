package ca.openbox.user.entities;

import ca.openbox.user.dataobject.UserDO;
import ca.openbox.user.dto.RegisterDTO;
import ca.openbox.user.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
@Data
@Configurable
public class User {
    private String username;
    private String name;
    private String password;
    private String roles;
    public UserDO getDO(){
        UserDO userDO = new UserDO();
        userDO.setUsername(username);
        userDO.setName(name);
        userDO.setPassword(password);
        userDO.setRoles(roles);
        return userDO;
    }
    static public User fromDO(UserDO userDO){
        User user = new User();
        user.username = userDO.getUsername();
        user.password = userDO.getPassword();
        user.roles = userDO.getRoles();
        user.name = userDO.getName();
        return user;
    }
    public static User fromRegisterDTO(RegisterDTO registerDTO){
        User user = new User();
        user.username = registerDTO.getUsername();
        user.name = registerDTO.getName();
        user.password = registerDTO.getPassword();
        user.roles = registerDTO.getRoles();
        return user;
    }

    @Autowired
    UserRepository userRepository;

    public User register(){
        UserDO userDO = userRepository.save(this.getDO());
        return this;
    }
}
