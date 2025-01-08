package ca.openbox.shift.controller;

import ca.openbox.shift.service.WorkRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shift/kpi-rate")
public class KPIRateController {
    @Autowired
    WorkRateService workRateService;

    @GetMapping
    public double getKPIRate() {
        return workRateService.getRate();
    }

    @PutMapping
    public void updateKPIRate(@RequestParam double rate) {
        workRateService.setRate(rate);
    }
}
