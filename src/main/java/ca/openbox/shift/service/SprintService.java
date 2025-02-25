package ca.openbox.shift.service;

import ca.openbox.shift.entities.Sprint;
import ca.openbox.infrastructure.variables.repository.ApplicationVariablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class SprintService {

    @Autowired
    private ApplicationVariablesRepository applicationVariablesRepository;


    public Sprint getCurrentSprint() {

        String startDateValue = applicationVariablesRepository.getApplicationVariableByKey("SprintBiweekStartDate").getValue();
        if (startDateValue == null) {
            throw new IllegalArgumentException("SprintBiweekStartDate key is not set in the application variables.");
        }

        LocalDate sprintStartDate = LocalDate.parse(startDateValue);

        ZonedDateTime startDateTime = sprintStartDate.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime endDateTime = startDateTime.plusDays(13);
        Sprint sprint = new Sprint();
        sprint.setStartTime(startDateTime);
        sprint.setEndTime(endDateTime);
        return sprint;
    }
}
