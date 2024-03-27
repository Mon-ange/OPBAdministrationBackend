package ca.openbox.process.controller;

import ca.openbox.process.dto.LeaveApplicationDTO;
import ca.openbox.process.entities.LeaveApplication;
import ca.openbox.process.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/process")
public class LeaveApplicationController {
    @Autowired
    LeaveApplicationService leaveApplicationService;
    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping ("/{username}/leaveApplication")
    public LeaveApplication leaveApplication(@PathVariable String username,
                                             @RequestBody LeaveApplicationDTO leaveApplicationDTO){
        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.setId(leaveApplicationDTO.getId());
        leaveApplication.setUsername(leaveApplicationDTO.getUsername());
        leaveApplication.setHandler(leaveApplicationDTO.getHandler());
        leaveApplication.setDateOfApplication(leaveApplicationDTO.getDateOfApplication());
        //associated to the business
        return leaveApplication;

    }

}
