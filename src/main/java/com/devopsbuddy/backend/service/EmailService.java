package com.devopsbuddy.backend.service;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendFeedbackEmail(FeedbackPojo feedbackPojo);

    void sendGenericEmailMessage(SimpleMailMessage message);
}
