package ca.openbox.shift.controller;

import ca.openbox.shift.service.StatutoryHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/shift/statutory-holidays")
public class StatutoryHolidayController {
    //make a dto the same as api: https://canada-holidays.ca/api/v1/provinces
    @Autowired
    private StatutoryHolidayService statutoryHolidayService;

    @GetMapping
    public List<LocalDate> getHolidays() {
        return statutoryHolidayService.getAllHolidayDates();
    }
}
