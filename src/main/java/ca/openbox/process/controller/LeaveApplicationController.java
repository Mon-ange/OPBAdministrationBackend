package ca.openbox.process.controller;

import ca.openbox.infrastructure.email.service.WebhookEmailService;
import ca.openbox.process.dto.LeaveApplicationDTO;
import ca.openbox.process.dto.PutLeaveApplicationDTO;
import ca.openbox.process.entities.LeaveApplication;
import ca.openbox.process.service.EmailService;
import ca.openbox.process.service.LeaveApplicationService;
import ca.openbox.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/process")
public class LeaveApplicationController {
    @Autowired
    LeaveApplicationService leaveApplicationService;
    @Autowired
    WebhookEmailService emailService;
    @Autowired
    UserRepository userRepository;
    @CrossOrigin(origins = "http://localhost:8081")
    @PutMapping("/application/leave-application")
    public LeaveApplication leaveApplication(@RequestBody PutLeaveApplicationDTO putLeaveApplicationDTO) throws Exception {
        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.setApplicant(putLeaveApplicationDTO.getApplicant());
        leaveApplication.setLeaveType(putLeaveApplicationDTO.getLeaveType());
        leaveApplication.setStart(putLeaveApplicationDTO.getStart());
        leaveApplication.setEnd(putLeaveApplicationDTO.getEnd());
        leaveApplication.setStatus("pending");
        leaveApplication.setSubmitTime(ZonedDateTime.now());
        leaveApplication.setCurrentHandler("raynold,agnes");
        leaveApplication.setReason(putLeaveApplicationDTO.getReason());
        String[] handlers = leaveApplication.getCurrentHandler().split(",");
        for(String handler:handlers){
            emailService.sendEmail(userRepository.getUserDOByUsernameAndActiveIsTrue(handler).getEmail(), "You have one new leave application to review","You have one new leave application to review. Please log on the https://openbox.brimon.me/ to review it.");
            Thread.sleep(500);//Fix with message queue
        }
        return leaveApplicationService.addLeaveApplication(leaveApplication);

    }

    @CrossOrigin(origins = "http://localhost:8081",methods = {RequestMethod.POST})
    @PostMapping("/application/{applicationID}/permit")
    public void permit(@PathVariable Integer applicationID){
        leaveApplicationService.permitApplication(applicationID);
    }

    @CrossOrigin(origins = "http://localhost:8081",methods = {RequestMethod.POST})
    @PostMapping("/application/{applicationID}/reject")
    public void reject(@PathVariable Integer applicationID,@RequestBody String rejectReason){
        leaveApplicationService.rejectApplication(applicationID,rejectReason);
    }
    @CrossOrigin(origins = "http://localhost:8081",methods = {RequestMethod.DELETE})
    @DeleteMapping("/application/{applicationID}")
    public void delete(@PathVariable Integer applicationID){
        leaveApplicationService.deleteApplication(applicationID);
    }

    @CrossOrigin(origins ="http://localhost:8081",methods = {RequestMethod.GET})
    @GetMapping("/application")
    public List<LeaveApplication> getApplicationsByApplicant(@RequestParam(value = "handler",required = false) String handler,
                                                             @RequestParam(value = "applicant",required = false) String applicant){
        return leaveApplicationService.getApplicationsByHandlerOrApplicant(handler,applicant);
    }
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/application/getAllApplications")
    public List<LeaveApplication> getAllApplications(){
        return leaveApplicationService.getAllApplications();
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PutMapping("/application/{applicationID}/note")
    public void putNote(@PathVariable Integer applicationID, @RequestBody String note){
        leaveApplicationService.addNoteToApplication(applicationID,note);
    }
}
