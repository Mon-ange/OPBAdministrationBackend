package ca.openbox.process.controller;

import ca.openbox.infrastructure.email.service.WebhookEmailService;
import ca.openbox.process.dto.LeaveApplicationDTO;
import ca.openbox.process.dto.PutLeaveApplicationDTO;
import ca.openbox.process.entities.LeaveApplication;
import ca.openbox.process.service.EmailService;
import ca.openbox.process.service.LeaveApplicationService;
import ca.openbox.process.service.components.ApplicationStatusChangeMessageQueue;
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
        LeaveApplication savedApplication = leaveApplicationService.addLeaveApplication(leaveApplication);
        ApplicationStatusChangeMessageQueue.put(savedApplication);
        return savedApplication;
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
        if(handler != null && !handler.equals("")){
            System.out.println(handler);
            return leaveApplicationService.getApplicationsByHandler(handler);
        }
        if(applicant !=null && !applicant.equals("")){
            return leaveApplicationService.getApplicationsByApplicant(applicant);
        }
        return leaveApplicationService.getAllApplications();
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PutMapping("/application/{applicationID}/note")
    public void putNote(@PathVariable Integer applicationID, @RequestBody String note){
        leaveApplicationService.addNoteToApplication(applicationID,note);
    }
}
