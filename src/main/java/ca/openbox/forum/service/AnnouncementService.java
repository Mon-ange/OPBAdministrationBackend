package ca.openbox.forum.service;

import ca.openbox.forum.dataobject.AnnouncementDO;
import ca.openbox.forum.entities.Announcement;
import ca.openbox.forum.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnnouncementService {
    @Autowired
    AnnouncementRepository announcementRepository;
    public Announcement addAnnouncement(Announcement announcement){
        AnnouncementDO announcementDO = announcementRepository.save(announcement.toDO());
        return Announcement.fromDO(announcementDO);
    }
    public List<Announcement> getAnnouncementAfter(ZonedDateTime expiryDate){
        List<AnnouncementDO> announcementListDO = new ArrayList<>();
        List<Announcement> announcements = new ArrayList<>();
        announcementListDO = announcementRepository.getAnnouncementDOByExpiryDateAfterOrderByCreatedTimeDesc(expiryDate);
        for (int i = 0;i<announcementListDO.size();++i){
            Announcement announcement = Announcement.fromDO(announcementListDO.get(i));
            announcements.add(announcement);

        }
        return announcements;


    }
    public Announcement getAnnouncementById(Integer announcementId){
        AnnouncementDO announcementDO = announcementRepository.getAnnouncementDOById(announcementId);
        return Announcement.fromDO(announcementDO);

    }
    public void deleteAnnouncementById(Integer announcementId){
        announcementRepository.deleteAnnouncementDOById(announcementId);
    }
}
