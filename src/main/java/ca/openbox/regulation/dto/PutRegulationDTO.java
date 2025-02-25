package ca.openbox.regulation.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class PutRegulationDTO {
    private String title;
    private String content;
    private ZonedDateTime modifiedTime;
}