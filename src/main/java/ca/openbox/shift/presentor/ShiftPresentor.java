package ca.openbox.shift.presentor;

import ca.openbox.shift.presentation.ShiftPresentation;
import ca.openbox.shift.repository.ShiftPresentationRepository;
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

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/getShiftByStartDateScope")
    public Collection<ShiftPresentation> getShiftByStartDateScope(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime start,
                                                                  @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime end){
        System.out.println("zone: " + start.getZone());
        System.out.println("start: " + start);
        System.out.println("end:" + end);
        return shiftPresentationRepository.getSchedulePresentationByStartdate(start, end);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/{username}/getShiftByStartDateScope")
    public Collection<ShiftPresentation> getMyShiftByStartDateScope(@PathVariable String username,
                                                                    @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime start,
                                                                    @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime end){
        return shiftPresentationRepository.getSchedulePresentationByUsernameAndStartdate(username,start,end);
    }

    public List<ShiftPresentation> getByGroupAndTimeScope(String groupName,
                                                          ZonedDateTime start,
                                                          ZonedDateTime end){
        return shiftPresentationRepository.getByGroupAndTimeScope(groupName,start,end);

    }

}
