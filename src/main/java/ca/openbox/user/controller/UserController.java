package ca.openbox.user.controller;

import ca.openbox.user.dataobject.UserDO;
import ca.openbox.user.dto.LoginDTO;
import ca.openbox.user.dto.RegisterDTO;
import ca.openbox.user.entities.User;
import ca.openbox.user.repository.UserRepository;
import ca.openbox.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
    @CrossOrigin(origins = "http://localhost:8081", allowCredentials="true", allowedHeaders = {"*"})
    @PostMapping("/login")
    public Object login(@RequestBody LoginDTO loginDTO, HttpServletRequest request){
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getUsername(),
                loginDTO.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        //return authenticationResponse.getPrincipal();
        return request.getSession(false).getId();
    }
    @Autowired
    UserService userService;
    @CrossOrigin(origins = "http://localhost:8081",methods = {RequestMethod.POST})
    @PostMapping("/register")
    public Object register(@RequestBody RegisterDTO registerDTO){
        User user = User.fromRegisterDTO(registerDTO);

        return userService.register(user);

    }

    @RequestMapping("/csrf")
    public CsrfToken csrf(CsrfToken token){
        return token;
    }
}
