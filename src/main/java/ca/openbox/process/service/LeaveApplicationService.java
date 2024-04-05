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
    public List<LeaveApplication> getApplicationsByHandlerOrApplicant(String handler, String applicant){
        List<LeaveApplication> leaveApplicationList = new ArrayList<>();
       // leaveApplicationRepository.getLeaveApplication
        List<LeaveApplicationDO> leaveApplicationDOList = leaveApplicationRepository.getLeaveApplicationDOByCurrentHandlerOrApplicant(handler,applicant);
        for(int i = 0; i<leaveApplicationDOList.size();++i){
            leaveApplicationList.add(LeaveApplication.fromDO(leaveApplicationDOList.get(i)));
        }
        return leaveApplicationList;
    }
}