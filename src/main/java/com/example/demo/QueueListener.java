package com.example.demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class QueueListener {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "testq")
    public void receivedMessage(QueueMessage qMsg) {
      //EmailService emailService = new EmailService();
        emailService.sendMail(qMsg.getEmail(), "Test", "Welcome!!");
    }

}
