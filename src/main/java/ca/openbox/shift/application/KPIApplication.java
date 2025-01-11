package ca.openbox.shift.application;

import ca.openbox.infrastructure.variables.repository.ApplicationVariablesRepository;
import ca.openbox.shift.entities.KPI;
import ca.openbox.shift.entities.ShiftArrangement;
import ca.openbox.shift.entities.Sprint;
import ca.openbox.shift.service.KPICalculator;
import ca.openbox.shift.service.ShiftArrangementService;
import ca.openbox.shift.service.SprintService;
import ca.openbox.statistics.presentation.WorkTimeStatistic;
import ca.openbox.statistics.service.WorkLoadCalculator;
import ca.openbox.statistics.service.WorkTimeStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;



@Component
public class KPIApplication {

    @Autowired
    private ShiftArrangementService shiftArrangementService;
    @Autowired
    private WorkTimeStatisticService workTimeStatisticService;
    @Autowired
    private WorkLoadCalculator workLoadCalculator;
    @Autowired
    private KPICalculator kpiCalculator;
    @Autowired
    private ApplicationVariablesRepository applicationVariablesRepository;
    @Autowired
    SprintService sprintService;


    public KPI ofDate(String group, Date date){
        ZonedDateTime zonedDateTime = date.toInstant().atZone(ZoneId.systemDefault());
        List<ShiftArrangement> dayShiftList = shiftArrangementService.getByGroupAndDate(group, zonedDateTime);
        Double dayTotalWorkTime = workLoadCalculator.calculateTotalWorkHour(dayShiftList);
       // System.out.println(dayTotalWorkTime);
        return kpiCalculator.calculate(Double.valueOf(0), dayTotalWorkTime);
    }
    public KPI ofBiweek(String group){

        Sprint sprint =sprintService.getCurrentSprint();

        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(
                sprint.getStartTime().toLocalDate(),
                sprint.getEndTime().toLocalDate()
        ) + 1;

        Double totalWorkTime = 0.0;
        for (int i = 0; i < daysBetween; i++) {
            ZonedDateTime currentDate = sprint.getStartTime().plusDays(i);
            List<ShiftArrangement> dayShiftList = shiftArrangementService.getByGroupAndDate(group, currentDate);
            Double dayWorkTime = workLoadCalculator.calculateTotalWorkHour(dayShiftList);
            totalWorkTime += dayWorkTime;
        }

        KPI biweekKPI = kpiCalculator.calculate(Double.valueOf(0), totalWorkTime);
        biweekKPI.setStartDateTime(sprint.getStartTime());
        biweekKPI.setEndDateTime(sprint.getEndTime());
        return biweekKPI;
    }
}
