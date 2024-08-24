package ca.openbox.shift.application;

import ca.openbox.shift.entities.KPI;
import ca.openbox.shift.entities.ShiftArrangement;
import ca.openbox.shift.service.KPICalculator;
import ca.openbox.shift.service.ShiftArrangementService;
import ca.openbox.statistics.presentation.WorkTimeStatistic;
import ca.openbox.statistics.service.WorkLoadCalculator;
import ca.openbox.statistics.service.WorkTimeStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


    public KPI ofDate(String group, Date date){
        ZonedDateTime zonedDateTime = date.toInstant().atZone(ZoneId.systemDefault());
        List<ShiftArrangement> dayShiftList = shiftArrangementService.getByGroupAndDate(group, zonedDateTime);
        Double dayTotalWorkTime = workLoadCalculator.calculateTotalWorkHour(dayShiftList);
       // System.out.println(dayTotalWorkTime);
        return kpiCalculator.calculate(Double.valueOf(0), dayTotalWorkTime);
    }
}
