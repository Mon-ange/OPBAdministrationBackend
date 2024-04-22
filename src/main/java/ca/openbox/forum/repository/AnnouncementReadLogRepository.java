package ca.openbox.forum.repository;

import ca.openbox.forum.dataobject.AnnouncementReadLogDO;
import ca.openbox.forum.dataobject.key.AnnouncementReadLogKey;
import ca.openbox.forum.entities.AnnouncementReadLog;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface AnnouncementReadLogRepository extends Repository<AnnouncementReadLogDO, AnnouncementReadLogKey> {
    List<AnnouncementReadLogDO> getAnnouncementReadLogDOByAnnouncementReadLogKey_Reader(String reader);
    AnnouncementReadLogDO save(AnnouncementReadLogDO announcementReadLogDO);
}
