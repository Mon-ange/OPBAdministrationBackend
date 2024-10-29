package ca.openbox.infrastructure.email.service;

import ca.openbox.infrastructure.email.dto.WebhookEmailDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Service
public class WebhookEmailService {
    @Value("${mail.url}")
    private String url;
    @Value("${mail.webtoken}")
    private String webtoken;
    public void sendEmail(String recipient, String subject, String content) throws IOException, InterruptedException {
        String endpoint=String.format(url);

        String emailJson = String.format("""
            {
                "title":"%s",
                "content":"%s",
                "recipient":"%s",
                "token":"%s"
            }
        """, subject, content, recipient,webtoken);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Authorization", "Bearer " + webtoken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(emailJson))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response code: " + response.statusCode());
        System.out.println("Response body: " + response.body());


    }
}
