package az.bakhishli.redditclone.service;

import az.bakhishli.redditclone.domain.NotificationEmail;

public interface MailService {
    void sendMail(NotificationEmail notificationEmail);
}
