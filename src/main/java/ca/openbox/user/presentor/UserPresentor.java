package ca.openbox.user.presentor;

import ca.openbox.user.presentation.UserPresentation;
import ca.openbox.user.repository.UserPresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/presentor/user")
public class UserPresentor {
    @Autowired
    UserPresentationRepository userPresentationRepository;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/getUserByRoleName")
    public Collection<UserPresentation> getUserByRoleName(@RequestParam("role") String role){
        return userPresentationRepository.findByRolesLikeAndActiveIsTrue("%"+role+"%");
    }
/*    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/getUserByUsername")
    public UserPresentor getUserByUsername(@RequestParam("username") String username){
        return userPresentationRepository.findById(username);
    }*/
}
