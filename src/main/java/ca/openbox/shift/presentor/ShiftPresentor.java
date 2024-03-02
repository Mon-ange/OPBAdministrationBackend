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

@RestController
@RequestMapping("/presentor/shift/")
public class ShiftPresentor {
    @Autowired
    ShiftPresentationRepository shiftPresentationRepository;

    @CrossOrigin(origins = "http://localhost:8082")
    @GetMapping("/getShiftByStartDateScope")
    public Collection<ShiftPresentation> getShiftByStartDateScope(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime start,
                                                                  @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime end){
        System.out.println("zone: " + start.getZone());
        return shiftPresentationRepository.getSchedulePresentationByStartdate(start, end);
    }

}
