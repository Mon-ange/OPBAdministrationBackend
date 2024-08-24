package ca.openbox.shift.controller;

import ca.openbox.shift.application.KPIApplication;
import ca.openbox.shift.entities.KPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/shift/kpi")

public class KPIController {
    @Autowired
    KPIApplication kpiApplication;
    @GetMapping
    public KPI getKPIByDateAndGroup(@RequestParam String group,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date date){
        return kpiApplication.ofDate(group, date);
    }
}
