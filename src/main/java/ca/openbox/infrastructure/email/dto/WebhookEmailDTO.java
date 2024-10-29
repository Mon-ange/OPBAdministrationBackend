package ca.openbox.infrastructure.email.dto;

import lombok.Data;

@Data
public class WebhookEmailDTO {
    private String recipient;
    private String title;
    private String content;
    private String token;
}
