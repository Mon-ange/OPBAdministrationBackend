package ca.openbox.user.entities;

import ca.openbox.user.dataobject.UserDO;
import ca.openbox.user.dto.RegisterDTO;
import ca.openbox.user.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.io.File;
import java.util.Date;

@Data
@Configurable
public class User {
//Domain Entity
    private String username;
    private String name;
    private String password;
    private Date birthdate;
    private String roles;
    private String legalname;
    private String sinno;
    private String address;
    private String phoneNumber;
    private String email;
    private String personalDocumentsPath;
    private File personalDocuments;

    public UserDO getDO(){
        UserDO userDO = new UserDO();
        userDO.setUsername(username);
        userDO.setName(name);
        userDO.setPassword(password);
        userDO.setBirthdate(birthdate);
        userDO.setRoles(roles);
        userDO.setLegalname(legalname);
        userDO.setSinno(sinno);
        userDO.setAddress(address);
        userDO.setPhoneNumber(phoneNumber);
        userDO.setEmail(email);
        //userDO.setPersonalDocumentsPath(personalDocumentsPath);
        //wait for list

        return userDO;
    }
    static public User fromDO(UserDO userDO){
        User user = new User();
        user.username = userDO.getUsername();
        user.password = userDO.getPassword();
        user.birthdate = userDO.getBirthdate();
        user.roles = userDO.getRoles();
        user.name = userDO.getName();
        user.legalname = userDO.getLegalname();
        user.sinno = userDO.getSinno();
        user.address = userDO.getAddress();
        user.phoneNumber = userDO.getPhoneNumber();
        user.email = userDO.getEmail();
        //user.personalDocumentsPath = userDO.getPersonalDocumentsPath();
        return user;
    }


    @Autowired
    UserRepository userRepository;

    public User register(){
        UserDO userDO = userRepository.save(this.getDO());
        return this;
    }
}
