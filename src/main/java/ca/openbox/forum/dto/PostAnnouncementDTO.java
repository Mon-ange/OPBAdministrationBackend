package ca.openbox.forum.dto;

import lombok.Data;

import java.time.ZonedDateTime;
@Data
public class PostAnnouncementDTO {
    private String title;
    private String content;
    private String publisher;
    private ZonedDateTime expiryDate;
    //new
    private String groupName;
}
