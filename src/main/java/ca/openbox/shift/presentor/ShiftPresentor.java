package ca.openbox.shift.presentor;

import ca.openbox.shift.presentation.ShiftPresentation;
import ca.openbox.shift.repository.ShiftPresentationRepository;
import ca.openbox.user.dataobject.UserDO;
import ca.openbox.user.entities.User;
import ca.openbox.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/presentor/shift/")
public class ShiftPresentor {
    @Autowired
    ShiftPresentationRepository shiftPresentationRepository;

    @Autowired
    UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/getShiftByStartDateScope")
    public Collection<ShiftPresentation> getShiftByStartDateScope(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime start,
                                                                  @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime end){
        System.out.println("zone: " + start.getZone());
        System.out.println("start: " + start);
        System.out.println("end:" + end);
        return shiftPresentationRepository.getByTimeScope(start, end);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/{username}/getShiftByStartDateScope")
    public Collection<ShiftPresentation> getMyShiftByStartDateScope(@PathVariable String username,
                                                                    @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime start,
                                                                    @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime end){
        return shiftPresentationRepository.getSchedulePresentationByUsernameAndStartdate(username,start,end);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/{username}/getShiftByStartDateScopeAndGroup")
    //only use userName because the frontend don't know the group of the user
    public Collection<ShiftPresentation> getShiftByStartDateScopeAndGroup(@PathVariable String username,
                                                                    @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime start,
                                                                    @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime end
                                                                    ){
        UserDO userDO = userRepository.getUserDOByUsernameAndActiveIsTrue(username);
        String groupName = userDO.getGroupName();
        if(groupName.equals("manager")){
            return shiftPresentationRepository.getByTimeScope(start, end);
        }
        else{
            return shiftPresentationRepository.getByGroupAndTimeScope(groupName,start,end);
        }
    }
}
