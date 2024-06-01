package ca.openbox.statistics.presentor;

import ca.openbox.shift.presentation.ShiftPresentation;
import ca.openbox.shift.presentor.ShiftPresentor;
import ca.openbox.statistics.presentation.WorkTimeStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
@RestController
@RequestMapping("/presentor/statistic/work-time-statistic")
public class WorkTimeStatisticsPresentor {
    @Autowired
    ShiftPresentor shiftPresentor;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/group/{groupname}")
    public List<WorkTimeStatistic> getByGroupAndTimeScope(@PathVariable String groupname,
                                                          @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime start,
                                                          @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime end) {
        //Calculate the data from ShiftPresentations retrieved by ShiftPresentor
        List<WorkTimeStatistic> resultList = new ArrayList<>();
        HashMap<String, WorkTimeStatistic> resultMap = new HashMap<>();
        List<ShiftPresentation> shiftList = shiftPresentor.getByGroupAndTimeScope(groupname, start, end);
        for (int i = 0; i < shiftList.size(); ++i) {
            WorkTimeStatistic workTimeStatistic = new WorkTimeStatistic();
            String username = shiftList.get(i).getUsername();

            workTimeStatistic.setUsername(username);
            workTimeStatistic.setUserRealName(shiftList.get(i).getUserRealName());
            Duration duration = Duration.between(shiftList.get(i).getStart(), shiftList.get(i).getEnd());
            if(resultMap.containsKey(username)){
                duration = duration.plusMinutes(resultMap.get(username).getMinutes());
            }
            if(duration.toMinutes()>=300){//5hours on work up minus lunch
            //Raynold said that he manually added.
                duration= duration.minusMinutes(30);
            }

            workTimeStatistic.setMinutes(duration.toMinutes());
            resultMap.put(username,workTimeStatistic);
        }
        for(WorkTimeStatistic workTimeStatistic:resultMap.values()){
            workTimeStatistic.setHours(workTimeStatistic.getMinutes()/60.0);
            resultList.add(workTimeStatistic);
        }
        return resultList;
    }
}
