package ca.openbox.shift.dataobject;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="opb_kpi_records")
public class KPIRecordDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int expected;

    private int actual;
}
