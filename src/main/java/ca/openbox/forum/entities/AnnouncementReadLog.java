package ca.openbox.forum.entities;

import ca.openbox.forum.dataobject.AnnouncementReadLogDO;
import ca.openbox.forum.dataobject.key.AnnouncementReadLogKey;
import lombok.Data;

import java.time.ZonedDateTime;
@Data
public class AnnouncementReadLog {
    private Integer announcementId;
    private String reader;
    private ZonedDateTime readTime;
    static public AnnouncementReadLog fromDO(AnnouncementReadLogDO announcementReadLogDO){
        AnnouncementReadLog announcementReadLog= new AnnouncementReadLog();
        announcementReadLog.announcementId = announcementReadLogDO.getAnnouncementReadLogKey().getAnnouncementId();
        announcementReadLog.readTime = announcementReadLogDO.getReadTime();
        announcementReadLog.reader = announcementReadLogDO.getAnnouncementReadLogKey().getReader();
        return announcementReadLog;
    }
    public AnnouncementReadLogDO toDO(){
        AnnouncementReadLogDO announcementReadLogDO = new AnnouncementReadLogDO();
        announcementReadLogDO.setAnnouncementReadLogKey(new AnnouncementReadLogKey(announcementId, reader));
        announcementReadLogDO.setReadTime(readTime);
        //announcementReadLogDO.setTag("tag");
        return announcementReadLogDO;
    }

}
