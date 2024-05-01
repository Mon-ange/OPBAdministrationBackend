package ca.openbox.user.service;

import ca.openbox.infrastructure.security.Cryptor;
import ca.openbox.user.dataobject.UserDO;
import ca.openbox.user.entities.User;
import ca.openbox.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    Cryptor cryptor;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userRepository.getUserDOByUsernameAndActiveIsTrue(username);
        System.out.println(userDO.getRoles());
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(userDO.getUsername()).password(userDO.getPassword()).roles(userDO.getRoles().split("|")).build();
        return userDetails;
    }
    public User register(User user) throws Exception{
        UserDO userDO = user.getDO();
        userDO.setPassword(passwordEncoder.encode(userDO.getPassword()));
        userDO.setSinno(cryptor.encrypt(userDO.getSinno()));
        userRepository.save(userDO);
        return user;
    }
    public void updateSIN(String username,String sinno) throws Exception{
        User user = getUserByUsername(username);
        user.setSinno(cryptor.encrypt(sinno));
        save(user);

    }
    public void setPassword(String username, String password){
        User user = getUserByUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        save(user);
    }
    public String getSinnoByUsername(String username) throws Exception {
        User user = getUserByUsername(username);
        String sinno = cryptor.decrypt(user.getSinno());
        return sinno;
    }
    public void save(User user){
        UserDO userDO = user.getDO();
        userRepository.save(userDO);
    }
    public User getUserByUsername(String username){
        UserDO userDO = userRepository.getUserDOByUsernameAndActiveIsTrue(username);
        User user = User.fromDO(userDO);
        return user;
    }

}
