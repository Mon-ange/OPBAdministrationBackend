package ca.openbox.shift.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ca.openbox.shift.dataobject.KPIRecordDO;
@Data
public class KPIRecord {

    private Long id;

    private String title;

    private int expected;

    private int actual;

    @JsonIgnore
    public KPIRecordDO getDO() {
        KPIRecordDO kpiRecordDO = new KPIRecordDO();
        kpiRecordDO.setId(this.id);
        kpiRecordDO.setTitle(this.title);
        kpiRecordDO.setExpected(this.expected);
        kpiRecordDO.setActual(this.actual);
        return kpiRecordDO;
    }

    public static KPIRecord fromDO(KPIRecordDO kpiRecordDO) {
        KPIRecord kpiRecord = new KPIRecord();
        kpiRecord.id = kpiRecordDO.getId();
        kpiRecord.title = kpiRecordDO.getTitle();
        kpiRecord.expected = kpiRecordDO.getExpected();
        kpiRecord.actual = kpiRecordDO.getActual();
        return kpiRecord;
    }
}
