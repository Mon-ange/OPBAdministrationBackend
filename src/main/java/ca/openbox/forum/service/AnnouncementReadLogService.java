package ca.openbox.forum.service;

import ca.openbox.forum.dataobject.AnnouncementReadLogDO;
import ca.openbox.forum.entities.AnnouncementReadLog;
import ca.openbox.forum.repository.AnnouncementReadLogRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnouncementReadLogService {
    @Autowired
    AnnouncementReadLogRepository announcementReadLogRepository;
    public List<AnnouncementReadLog> getAnnouncementReadLogByReader(String reader){
        List<AnnouncementReadLogDO> announcementReadLogDOList = new ArrayList<>();
        announcementReadLogDOList = announcementReadLogRepository.getAnnouncementReadLogDOByAnnouncementReadLogKey_Reader(reader);
        List<AnnouncementReadLog> announcementReadLogList = new ArrayList<>();
        for(int i=0;i<announcementReadLogDOList.size();++i){
            AnnouncementReadLog announcementReadLog = AnnouncementReadLog.fromDO(announcementReadLogDOList.get(i));
            announcementReadLogList.add(announcementReadLog);
        }
        return announcementReadLogList;
    }
    @Transactional
    public void addAnnouncementReadLog(AnnouncementReadLog announcementReadLog){
        System.out.println(announcementReadLog.toDO().toString());
        announcementReadLogRepository.save(announcementReadLog.toDO());
    }
}
