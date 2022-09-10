package com.sam.lab4.controller;

import com.sam.lab4.model.Mail;
import com.sam.lab4.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/mail")
public class MailController {
    @Autowired
    MailService mailService;

    @GetMapping("/sendmail")
    public String testMail(){
        try {
            mailService.sendMail(new Mail());
            System.out.println("send mail successfull");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/list-user";
    }
}
