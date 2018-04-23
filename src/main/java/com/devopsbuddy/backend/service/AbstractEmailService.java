package com.devopsbuddy.backend.service;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.to.address}")
    private String defaultToAddress;

    protected SimpleMailMessage prepareSimpleMailMessageFromFeedbackPojo(FeedbackPojo pojo){
        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo(defaultToAddress);
        message.setFrom(pojo.getEmail());
        message.setSubject("[DevOps Buddy]: Feedback received from "+ pojo.getFirstName() +
                " " + pojo.getLastName() + "!");
        message.setText(pojo.getFeedback());
        return message;
    }

    @Override
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo) {
        sendGenericEmailMessage(prepareSimpleMailMessageFromFeedbackPojo(feedbackPojo));
    }
}
