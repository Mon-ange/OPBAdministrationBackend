package ca.openbox.process.repository;

import ca.openbox.process.dataobject.LeaveApplicationDO;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface LeaveApplicationRepository extends Repository<LeaveApplicationDO,Integer> {
    LeaveApplicationDO save(LeaveApplicationDO leaveApplicationDO);
    LeaveApplicationDO getLeaveApplicationDOById(Integer id);
    List<LeaveApplicationDO> getLeaveApplicationDOByCurrentHandlerOrApplicantOrderBySubmitTimeDesc(String currentHandler, String applicant);
    List<LeaveApplicationDO> getLeaveApplicationDOByStatusIsNotContainingOrderBySubmitTimeDesc(String stauts);
    void deleteById(Integer id);
}
