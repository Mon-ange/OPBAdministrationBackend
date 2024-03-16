package ca.openbox.user.service;

import ca.openbox.user.dataobject.UserDO;
import ca.openbox.user.dto.UserDTO;
import ca.openbox.user.entities.User;
import ca.openbox.user.repository.UserRepository;
import org.apache.tomcat.util.security.MD5Encoder;
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
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userRepository.getUserDOByUsername(username);
        System.out.println(userDO.getRoles());
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(userDO.getUsername()).password(userDO.getPassword()).roles(userDO.getRoles().split("|")).build();
        return userDetails;
    }
    public User register(User user){
        UserDO userDO = user.getDO();
        userDO.setPassword(passwordEncoder.encode(userDO.getPassword()));
        userRepository.save(userDO);
        return user;
    }

    public User getUserByUsername(String username){
        UserDO userDO = userRepository.getUserDOByUsername(username);
        User user = User.fromDO(userDO);
        return user;
    }
}
