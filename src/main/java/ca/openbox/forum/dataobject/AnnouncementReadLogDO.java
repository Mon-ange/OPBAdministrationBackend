package ca.openbox.forum.dataobject;

import ca.openbox.forum.dataobject.key.AnnouncementReadLogKey;
import ca.openbox.user.dataobject.UserDO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
@Data
@EqualsAndHashCode
@Entity
@Table(name="opb_announcement_readlog")
public class AnnouncementReadLogDO {
    @EmbeddedId
    AnnouncementReadLogKey announcementReadLogKey;

    @Column(name = "read_time")
    ZonedDateTime readTime;



}
