package ca.openbox.shift.service;

import ca.openbox.infrastructure.varaibles.repository.ApplicationVariablesRepository;
import ca.openbox.infrastructure.varaibles.service.ApplicationVariableService;
import ca.openbox.shift.dataobject.EmployeePreferWorkdayDO;
import ca.openbox.shift.entities.EmployeePreferWorkday;
import ca.openbox.shift.entities.EmployeePreferWorkdayBoard;
import ca.openbox.shift.repository.EmployeePreferWorkdayRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class EmployeePreferWorkdayBoardService {
    @Autowired
    EmployeePreferWorkdayRepository employeePreferWorkdayRepository;
    @Autowired
    ApplicationVariableService applicationVariableService;
    public List<String> getPreferredEmployeesBydate(ZonedDateTime date) {
        EmployeePreferWorkdayBoard board = EmployeePreferWorkdayBoard.getByMonth(employeePreferWorkdayRepository, date.getMonthValue());
        return board.getPreferredEmployeesBydate(date);
    }
    public void updatePreferWorkday(String username,Integer currentMonth,List<ZonedDateTime> dates){
        EmployeePreferWorkdayBoard board = EmployeePreferWorkdayBoard.getByMonth(employeePreferWorkdayRepository,currentMonth);
        board.updatePreferWorkday(employeePreferWorkdayRepository,username,dates);
    }

    public void shiftToNextMonth(){
        Integer currentMonth = Integer.valueOf(applicationVariableService.getVariableValue("shift_select_month"))+1;
        applicationVariableService.setVariableValue("shift_select_month",String.valueOf(currentMonth));

    }
    public Integer getCurrentMonth(){
       String currentMonth =  applicationVariableService.getVariableValue("shift_select_month");
       return Integer.valueOf(currentMonth);
    }
}
