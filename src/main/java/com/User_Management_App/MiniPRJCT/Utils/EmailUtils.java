package com.User_Management_App.MiniPRJCT.Utils;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
    @Autowired
    private JavaMailSender mailSender;

    public boolean sendMail(String subject, String body, String to) {
    boolean isSent=false;
    try{
       MimeMessage message= mailSender.createMimeMessage();
       MimeMessageHelper helper=new MimeMessageHelper(message);
       helper.setTo(to);
       helper.setSubject(subject);
       helper.setText(body, true);
       mailSender.send(message);
       isSent=true;

    }
    catch(Exception e){
    e.printStackTrace();
    }
    return  isSent;
    }
}
