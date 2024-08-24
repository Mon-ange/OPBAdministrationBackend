package ca.openbox.statistics.service;

import ca.openbox.shift.entities.ShiftArrangement;
import ca.openbox.shift.presentation.ShiftPresentation;
import ca.openbox.shift.service.ShiftArrangementService;
import ca.openbox.statistics.presentation.WorkTimeStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class WorkTimeStatisticService {

    public List<WorkTimeStatistic> calculate(List<ShiftArrangement> shiftList){
        List<WorkTimeStatistic> resultList = new ArrayList<>();
        HashMap<String, WorkTimeStatistic> resultMap = new HashMap<>();
        for (int i = 0; i < shiftList.size(); ++i) {
            WorkTimeStatistic workTimeStatistic = new WorkTimeStatistic();
            String username = shiftList.get(i).getUsername();

            workTimeStatistic.setUsername(username);
            Duration duration = Duration.between(shiftList.get(i).getStart(), shiftList.get(i).getEnd());
            if(duration.toMinutes()>300){//5hours on work up minus lunch
                duration= duration.minusMinutes(30);
            }
            if(resultMap.containsKey(username)){
                duration = duration.plusMinutes(resultMap.get(username).getMinutes());
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
