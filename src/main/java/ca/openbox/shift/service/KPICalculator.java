package ca.openbox.shift.service;

import ca.openbox.shift.entities.KPI;
import org.springframework.stereotype.Service;

@Service
public class KPICalculator {

    public KPI calculate(Double monthWorkHour, Double dayWorkHour){
        //Calculate KPI
        KPI kpi = new KPI();
        kpi.setTarget((int) Math.ceil(dayWorkHour * 2.0));
        kpi.setBonus(0);
        return kpi;
    }
}
