package ca.openbox.forum.controller;

import ca.openbox.forum.dto.PostAnnouncementDTO;
import ca.openbox.forum.dto.PutAnnouncementDTO;
import ca.openbox.forum.entities.Announcement;
import ca.openbox.forum.service.AnnouncementService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
@Transactional
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    @Autowired
    AnnouncementService announcementService;
    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping
    public Announcement addAnnouncement(@RequestBody PostAnnouncementDTO postAnnouncementDTO){
        Announcement announcement = new Announcement();
        announcement.setTitle(postAnnouncementDTO.getTitle());
        announcement.setContent(postAnnouncementDTO.getContent());
        announcement.setPublisher(postAnnouncementDTO.getPublisher());
        announcement.setExpiryDate(postAnnouncementDTO.getExpiryDate());
        announcement.setCreatedTime(ZonedDateTime.now());
        return announcementService.addAnnouncement(announcement);
    }
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping
    public List<Announcement> getAnnouncementAfter(@RequestParam("expireAfter") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime expiryDate){
        return announcementService.getAnnouncementAfter(expiryDate);
    }
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/{announcementId}")
    public Announcement getAnnouncementById(@PathVariable("announcementId") Integer announcementId){
        return announcementService.getAnnouncementById(announcementId);
    }
    @CrossOrigin(origins = "http://localhost:8081")
    @DeleteMapping("/{announcementId}")
    public void deleteAnnouncementById(@PathVariable("announcementId") Integer announcementId){
        announcementService.deleteAnnouncementById(announcementId);
    }
    @CrossOrigin(origins = "http://localhost:8081")
    @PutMapping("/{announcementId}")
    public void putAnnouncementById(@PathVariable("announcementId") Integer announcementId,
                                            @RequestBody PutAnnouncementDTO putAnnouncementDTO)
    {
        Announcement oldAnnouncement = announcementService.getAnnouncementById(announcementId);
        Announcement announcement = new Announcement();
        announcement.setId(announcementId);
        announcement.setPublisher(oldAnnouncement.getPublisher());
        announcement.setCreatedTime(oldAnnouncement.getCreatedTime());
        announcement.setTitle(putAnnouncementDTO.getTitle());
        announcement.setContent(putAnnouncementDTO.getContent());
        announcement.setExpiryDate(putAnnouncementDTO.getExpiryDate());
        announcementService.addAnnouncement(announcement);
    }
}
