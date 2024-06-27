package ca.openbox.shift.controller;

import ca.openbox.shift.dto.EmployeePreferWorkdaysDTO;
import ca.openbox.shift.service.EmployeePreferWorkdayBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shift/shiftboard")
public class ShiftBoardController {
    @Autowired
    EmployeePreferWorkdayBoardService employeePreferWorkdayBoardService;
    @CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
    @GetMapping("/getBoardByDate")
    public List<String> getPreferredEmployeesBydate(@RequestParam(value="date") ZonedDateTime date){
        return employeePreferWorkdayBoardService.getPreferredEmployeesBydate(date);
    }
    @CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
    @GetMapping("/getBoardByUser")
    public List<ZonedDateTime> getPreferredDateByUser(@RequestParam(value="username") String username){
        List<ZonedDateTime> preferredDates = employeePreferWorkdayBoardService.getPreferredDateByUser(username)
                .stream().map(t -> {
                    return t.withZoneSameLocal(ZoneId.of("America/Los_Angeles"));
                }).collect(Collectors.toList());
        return preferredDates;
    }

    @CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
    @PutMapping("/updateBoard")
    public void updatePreferWorkday(@RequestBody EmployeePreferWorkdaysDTO employeePreferWorkdaysDTO){
        employeePreferWorkdayBoardService.updatePreferWorkday(employeePreferWorkdaysDTO.getUsername(),
                employeePreferWorkdaysDTO.getCurrentMonth(),
                employeePreferWorkdaysDTO.getDates());
    }
    @CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
    @PutMapping("/shiftToNextMonth")
    public void shiftToNextMonth(){
        employeePreferWorkdayBoardService.shiftToNextMonth();
    }
    @CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
    @GetMapping("/getCurrentMonth")
    public Integer getCurrentMonth(){
        return employeePreferWorkdayBoardService.getCurrentMonth();
    }
}
