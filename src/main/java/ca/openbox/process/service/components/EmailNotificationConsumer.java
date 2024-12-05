package ca.openbox.process.service.components;

import ca.openbox.infrastructure.email.service.WebhookEmailService;
import ca.openbox.process.entities.LeaveApplication;
import ca.openbox.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationConsumer {
    private final WebhookEmailService emailService;
    private final UserRepository userRepository;

    public EmailNotificationConsumer(WebhookEmailService emailService, UserRepository userRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        startConsumer();
    }

    private void startConsumer() {
        new Thread(() -> {
            while (true) {
                try {
                    LeaveApplication leaveApplication = ApplicationStatusChangeMessageQueue.take();
                    String[] handlers = leaveApplication.getCurrentHandler().split(",");
                    for (String handler : handlers) {
                        String email = userRepository.getUserDOByUsernameAndActiveIsTrue(handler).getEmail();
                        emailService.sendEmail(
                                email,
                                "You have one new leave application to review",
                                "You have one new leave application to review. Please log on the https://openbox.brimon.me/ to review it."
                        );
                        Thread.sleep(20000);//sleep 20s
                    }
                } catch (Exception e) {
                    e.printStackTrace(); // 这里可以加上日志记录
                }
            }
        }).start();
    }
}
