package com.sam.lab4.service;

import com.sam.lab4.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerResponse;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine springTemplateEngine;
    public void sendMail(Mail mail) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED, "UTF-8");
        Context context = new Context();
        String html = springTemplateEngine.process("email-template", context);
        mimeMessageHelper.setFrom("hien01kc@gmail.com");
        mimeMessageHelper.setTo(mail.getTo());
        mimeMessageHelper.setSubject("[user] kiem tra thong tin");
        mimeMessageHelper.setText(html, true);
        javaMailSender.send(message);
    }
}
