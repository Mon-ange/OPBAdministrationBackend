package ca.openbox.forum.dataobject;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
@Data
@Entity
@Table(name="opb_announcement")
public class AnnouncementDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String publisher;
    private ZonedDateTime createdTime;
    private ZonedDateTime expiryDate;
}
