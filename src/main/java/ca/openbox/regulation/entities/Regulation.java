package ca.openbox.regulation.entities;

import ca.openbox.regulation.dataobject.RegulationDO;
import lombok.Data;

import java.time.ZonedDateTime;
@Data
public class Regulation {
    private Integer id;
    private String title;
    private String content;
    private ZonedDateTime modifiedTime;

    public RegulationDO toDO() {
        RegulationDO regulationDO = new RegulationDO();
        regulationDO.setId(id);
        regulationDO.setTitle(title);
        regulationDO.setContent(content);
        regulationDO.setModifiedTime(modifiedTime);
        return regulationDO;
    }

    public static Regulation fromDO(RegulationDO regulationDO) {
        Regulation regulation = new Regulation();
        regulation.id = regulationDO.getId();
        regulation.title = regulationDO.getTitle();
        regulation.content = regulationDO.getContent();
        regulation.modifiedTime = regulationDO.getModifiedTime();
        return regulation;
    }
}
