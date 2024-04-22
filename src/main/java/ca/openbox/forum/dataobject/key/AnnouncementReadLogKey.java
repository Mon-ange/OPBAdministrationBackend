package ca.openbox.forum.dataobject.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementReadLogKey implements Serializable {

    private Integer announcementId;
    private String reader;

}
