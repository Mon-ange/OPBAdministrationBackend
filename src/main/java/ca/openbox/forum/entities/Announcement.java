package ca.openbox.forum.entities;

import ca.openbox.forum.dataobject.AnnouncementDO;
import lombok.Data;

import java.time.ZonedDateTime;
@Data
public class Announcement {
    private Integer id;
    private String title;
    private String content;
    private String publisher;
    private ZonedDateTime createdTime;
    private ZonedDateTime expiryDate;
    private String groupName;
    public AnnouncementDO toDO(){
        AnnouncementDO announcementDO = new AnnouncementDO();
        announcementDO.setId(id);
        announcementDO.setTitle(title);
        announcementDO.setContent(content);
        announcementDO.setPublisher(publisher);
        announcementDO.setCreatedTime(createdTime);
        announcementDO.setExpiryDate(expiryDate);
        announcementDO.setGroupName(groupName);
        return announcementDO;
    }
    static public Announcement fromDO(AnnouncementDO announcementDO){
        Announcement announcement = new Announcement();
        announcement.id = announcementDO.getId();
        announcement.content = announcementDO.getContent();
        announcement.createdTime = announcementDO.getCreatedTime();
        announcement.expiryDate = announcementDO.getExpiryDate();
        announcement.publisher = announcementDO.getPublisher();
        announcement.title = announcementDO.getTitle();
        announcement.groupName = announcementDO.getGroupName();
        return announcement;
    }


}
