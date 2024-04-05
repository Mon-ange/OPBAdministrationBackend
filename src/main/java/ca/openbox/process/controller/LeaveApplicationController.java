package ca.openbox.process.controller;

import ca.openbox.process.dto.LeaveApplicationDTO;
import ca.openbox.process.dto.PutLeaveApplicationDTO;
import ca.openbox.process.entities.LeaveApplication;
import ca.openbox.process.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/process")
public class LeaveApplicationController {
    @Autowired
    LeaveApplicationService leaveApplicationService;
    @CrossOrigin(origins = "http://localhost:8081")
    @PutMapping("/application/leave-application")
    public LeaveApplication leaveApplication(@RequestBody PutLeaveApplicationDTO putLeaveApplicationDTO){
        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.setApplicant(putLeaveApplicationDTO.getApplicant());
        leaveApplication.setLeaveType(putLeaveApplicationDTO.getLeaveType());
        leaveApplication.setStart(putLeaveApplicationDTO.getStart());
        leaveApplication.setEnd(putLeaveApplicationDTO.getEnd());
        leaveApplication.setStatus("active");
        leaveApplication.setSubmitTime(ZonedDateTime.now());
        leaveApplication.setCurrentHandler("Raynold");
        leaveApplication.setReason(putLeaveApplicationDTO.getReason());
        //associated to the business
        return leaveApplicationService.addLeaveApplication(leaveApplication);

    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/application/{applicationID}/permit")
    public void permit(@PathVariable Integer applicationID){
        leaveApplicationService.permitApplication(applicationID);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/application/{applicationID}/reject")
    public void reject(@PathVariable Integer applicationID,@RequestBody String rejectReason){
        leaveApplicationService.rejectApplication(applicationID,rejectReason);
    }

    @CrossOrigin(origins ="http://localhost:8081")
    @GetMapping("application")
    public List<LeaveApplication> getApplicationsByApplicant(@RequestParam(value = "handler",required = false) String handler,
                                                             @RequestParam(value = "applicant",required = false) String applicant){
        return leaveApplicationService.getApplicationsByHandlerOrApplicant(handler,applicant);
    }

}
