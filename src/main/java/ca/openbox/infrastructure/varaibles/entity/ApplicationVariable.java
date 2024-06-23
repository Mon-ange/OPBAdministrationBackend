package ca.openbox.infrastructure.varaibles.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="opb_application_variables")
public class ApplicationVariable {
    @Id
    @Column(name = "`key`")
    private String key;
    private String value;


}
