package ca.openbox.shift.service;

import ca.openbox.shift.entities.KPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KPICalculator {

    @Autowired
    WorkRateService workRateService;

    private Double getTVDailyWorkRate(){
        return workRateService.getRate();
    }

    public KPI calculate(Double monthWorkHour, Double dayWorkHour){
        //Calculate KPI
        KPI kpi = new KPI();
        kpi.setTarget((int) Math.ceil(dayWorkHour * getTVDailyWorkRate()));
        kpi.setBonus(0);
        return kpi;
    }
}
