package ca.openbox.forum.dto;

import lombok.Data;

import java.time.ZonedDateTime;
@Data
public class PutAnnouncementDTO {
    private String title;
    private String content;
    private ZonedDateTime expiryDate;
    //new
    private String groupName;
}
