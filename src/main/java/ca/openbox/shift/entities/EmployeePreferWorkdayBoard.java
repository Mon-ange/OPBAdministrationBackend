package ca.openbox.shift.entities;

import ca.openbox.shift.dataobject.EmployeePreferWorkdayDO;
import ca.openbox.shift.repository.EmployeePreferWorkdayRepository;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class EmployeePreferWorkdayBoard {
    private List<EmployeePreferWorkday> employeePreferWorkdays;
    private int currentMonth;

    private EmployeePreferWorkdayBoard(List<EmployeePreferWorkday> employeePreferWorkdays,int month){
        this.employeePreferWorkdays = employeePreferWorkdays;
        this.currentMonth = month;
        System.out.println("Retrieved Work Days from Repository:" + employeePreferWorkdays);
    }
    public List<ZonedDateTime> getPreferredDateByUser(String username){
        List<ZonedDateTime> dateTimeList = new ArrayList<>();
        for(int i = 0;i<employeePreferWorkdays.size();++i){
            if(employeePreferWorkdays.get(i).getName().equals(username)) dateTimeList.add(employeePreferWorkdays.get(i).getPreferDate());
        }
        return dateTimeList;
    }

    public static EmployeePreferWorkdayBoard getByMonth(EmployeePreferWorkdayRepository repository, int month){
        List<EmployeePreferWorkday> employeePreferWorkdays = repository.findAllByMonth(month).stream().map(o -> {
            return EmployeePreferWorkday.fromDO(o);
        }).collect(Collectors.toList());
        return new EmployeePreferWorkdayBoard(employeePreferWorkdays,month);
    }
    public void updatePreferWorkday(EmployeePreferWorkdayRepository repository,String username,List<ZonedDateTime> dates){
        //delete all the workday item including the username
        repository.deleteByNameAndMonth(username,currentMonth);
        System.out.println("Date from Frontend:" + dates);
        //insert
        for(int i = 0;i<dates.size();++i){
            //how to handle the id of do?
            EmployeePreferWorkdayDO employeePreferWorkdayDO = new EmployeePreferWorkdayDO();
            employeePreferWorkdayDO.setPreferDate(dates.get(i));
            employeePreferWorkdayDO.setName(username);
            repository.save(employeePreferWorkdayDO);
        }
    }
    public List<String> getPreferredEmployeesBydate(ZonedDateTime date){
        List<String> employees = new ArrayList<>();
        for(int i = 0;i<employeePreferWorkdays.size();++i){
            ZonedDateTime temp = employeePreferWorkdays.get(i).getPreferDate();
            if(temp.getMonthValue()==date.getMonthValue()&&
                    temp.getDayOfMonth()==date.getDayOfMonth()){
                //the specific time should be controlled in controller
                employees.add(employeePreferWorkdays.get(i).getName());
            }
        }
        return employees;
    }


}
