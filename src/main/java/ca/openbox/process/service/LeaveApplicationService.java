package ca.openbox.process.service;

import ca.openbox.process.dataobject.LeaveApplicationDO;
import ca.openbox.process.entities.LeaveApplication;
import ca.openbox.process.repository.LeaveApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveApplicationService {
    @Autowired
    LeaveApplicationRepository leaveApplicationRepository;
    public LeaveApplication addLeaveApplication(LeaveApplication leaveApplication){
        LeaveApplicationDO leaveApplicationDO = leaveApplicationRepository.save(leaveApplication.toDO());
        return LeaveApplication.fromDO(leaveApplicationDO);
    }
    public void permitApplication(Integer applicationID){
        LeaveApplicationDO leaveApplicationDO = leaveApplicationRepository.getLeaveApplicationDOById(applicationID);
        leaveApplicationDO.setStatus("approved");
        leaveApplicationDO.setCurrentHandler(leaveApplicationDO.getApplicant());
        leaveApplicationRepository.save(leaveApplicationDO);
    }
    public void rejectApplication(Integer applicationID, String rejectReason){
        LeaveApplicationDO leaveApplicationDO = leaveApplicationRepository.getLeaveApplicationDOById(applicationID);
        leaveApplicationDO.setStatus("rejected");
        leaveApplicationDO.setRejectReason(rejectReason);
        leaveApplicationDO.setCurrentHandler(leaveApplicationDO.getApplicant());
        leaveApplicationRepository.save(leaveApplicationDO);
    }
    public void deleteApplication(Integer applicationID){
        leaveApplicationRepository.deleteById(applicationID);
    }
    public List<LeaveApplication> getApplicationsByHandler(String handler){
        List<LeaveApplication> leaveApplicationList = new ArrayList<>();
       // leaveApplicationRepository.getLeaveApplication
        List<LeaveApplicationDO> leaveApplicationDOList = leaveApplicationRepository.getLeaveApplicationDOByCurrentHandlerContainingOrderBySubmitTimeDesc(handler);
        for(int i = 0; i<leaveApplicationDOList.size();++i){
            leaveApplicationList.add(LeaveApplication.fromDO(leaveApplicationDOList.get(i)));
        }
        return leaveApplicationList;
    }
    public List<LeaveApplication> getApplicationsByApplicant(String applicant){
        List<LeaveApplication> leaveApplicationList = new ArrayList<>();
        // leaveApplicationRepository.getLeaveApplication
        List<LeaveApplicationDO> leaveApplicationDOList = leaveApplicationRepository.getLeaveApplicationDOByApplicantOrderBySubmitTimeDesc(applicant);
        for(int i = 0; i<leaveApplicationDOList.size();++i){
            leaveApplicationList.add(LeaveApplication.fromDO(leaveApplicationDOList.get(i)));
        }
        return leaveApplicationList;
    }
    public List<LeaveApplication> getAllApplications(){
        List<LeaveApplication> leaveApplicationList = new ArrayList<>();
        List<LeaveApplicationDO> leaveApplicationDOList = leaveApplicationRepository.getLeaveApplicationDOByStatusIsNotContainingOrderBySubmitTimeDesc("pending");
        for(int i = 0; i<leaveApplicationDOList.size();++i){
            leaveApplicationList.add(LeaveApplication.fromDO(leaveApplicationDOList.get(i)));
        }
        return leaveApplicationList;
    }
    public void addNoteToApplication(Integer applicationID, String note){
        LeaveApplicationDO leaveApplicationDO = leaveApplicationRepository.getLeaveApplicationDOById(applicationID);
        leaveApplicationDO.setNote(note);
        leaveApplicationRepository.save(leaveApplicationDO);
    }
}