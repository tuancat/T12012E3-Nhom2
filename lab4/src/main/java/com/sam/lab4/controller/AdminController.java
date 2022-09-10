package com.sam.lab4.controller;

import com.sam.lab4.model.Mail;
import com.sam.lab4.model.User;
import com.sam.lab4.service.MailService;
import com.sam.lab4.service.UserRoleService;
import com.sam.lab4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;
    @Autowired
    private UserRoleService userRoleService;
    @GetMapping("/add-user")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("listRole", userRoleService.findAll());
        return "/admin/add-user";
    }

    @PostMapping("/save-add-user")
    public String saveAddUser(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap modelMap){
        if(!result.hasErrors()) {
            userService.add(user);
            return "redirect:/admin/list-user";
        }
        else{
            modelMap.addAttribute("error", false);
            modelMap.addAttribute("mess", "nhap sai roi con tró");
            return "/admin/add-user";
        }
    }
    @GetMapping("/list-user")
    public String listUser(Model model){
        model.addAttribute("listUser", userService.findAll());
        return "/admin/list-user";
    }

    @GetMapping("/sendmail/{id}")
    public String testMail(@PathVariable Long id, Model model) throws MessagingException {
            User user = userService.findById(id);
            Mail mail = new Mail();
            mail.setTo(user.getEmail());
            mailService.sendMail(mail);
            System.out.println("send mail successfull");
        return "redirect:/admin/list-user";
    }
}
