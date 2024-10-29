package ca.openbox.process.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.mail.smtp.SMTPTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Properties;
@Service
public class EmailService {

    @Value("${mail.clientID}")
    private static String CLIENT_ID;
    @Value("${mail.clientSC}")
    private static String CLIENT_CREDENTIALS;
    @Value("${mail.tenantID}")
    private String TENANT_ID;
    @Value("${mail.scope}")
    private static String SCOPE;
    private final String accessToken;

    public EmailService(
            @Value("${mail.token}") String TOKEN_URL,
            @Value("${mail.clientID}") String CLIENT_ID,
            @Value("${mail.scope}") String SCOPE,
            @Value("${mail.clientSC}") String CLIENT_CREDENTIALS
    ) throws Exception {
        this.accessToken = getAccessToken(TOKEN_URL,CLIENT_ID,SCOPE,CLIENT_CREDENTIALS);
    }

    public String getAccessToken(String TOKEN_URL,String CLIENT_ID,String SCOPE,String CLIENT_CREDENTIALS) throws Exception{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(TOKEN_URL))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(
                        "client_id=" + CLIENT_ID +
                                "&scope=" + SCOPE +
                                "&client_secret=" + CLIENT_CREDENTIALS +
                                "&grant_type=client_credentials"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> jsonMap = mapper.readValue(response.body(), Map.class);

        return jsonMap.get("access_token");
    }
    @Value("${mail.sender}")
    private String username;
    @Value("${mail.host}")
    private String host;
    private static String generateOAuth2Token(String email, String accessToken) {
        String formattedToken = String.format("user=%s\1auth=Bearer %s\1\1", email, accessToken);
        return new String(java.util.Base64.getEncoder().encode(formattedToken.getBytes()));
    }
    /* Graph AI
    public void sendEmail(String recipient, String subject, String messageBody) throws IOException, InterruptedException {
        System.out.println(accessToken);
        String endpoint=String.format("https://graph.microsoft.com/v1.0/sendMail");
        String emailJson = String.format("""
            {
                "message": {
                    "subject": "%s",
                    "body": {
                        "contentType": "Text",
                        "content": "%s"
                    },
                    "toRecipients": [
                        {
                            "emailAddress": {
                                "address": "%s"
                            }
                        }
                    ]
                }
            }
        """, subject, messageBody, recipient);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(emailJson))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }
     */
    /* SMTP
    public void sendEmail(String recipient, String subject, String messageBody) {


        Properties prop = new Properties();
        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.debug", "true");
        prop.put("mail.debug.auth", "true");
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.auth.mechanisms", "XOAUTH2");
        prop.put("mail.smtp.auth.login.disable", "true");
        prop.put("mail.smtp.auth.plain.disable", "true");
        prop.put("mail.smtp.auth.xoauth2.disable", "false");

        prop.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);



        //prop.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);

        Session session = Session.getInstance(prop,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(//);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient)
            );
            message.setSubject(subject);
            message.setText(messageBody);
            SMTPTransport transport = (SMTPTransport) session.getTransport("smtp");
            System.out.println(generateOAuth2Token(username, accessToken));
            transport.connect(host, username,null);
            transport.issueCommand("AUTH XOAUTH2 " + generateOAuth2Token(username, accessToken), 235);
            transport.sendMessage(message,message.getAllRecipients());
            Transport.send(message);

            System.out.println("Mail sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }*/

}
