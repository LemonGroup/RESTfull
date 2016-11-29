package org.lemongroup.lemonstat.rest.service;

import org.lemongroup.lemonstat.rest.datamodel.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification(Account account){
        //send email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(account.getEmail());
        mailMessage.setFrom("geekprojects1@gmail.com");
        mailMessage.setSubject("LemonStat password reset");
        mailMessage.setText("Account: " + account.getUsername() + ", new password: " + account.getPassword());

        javaMailSender.send(mailMessage);
    }
}
