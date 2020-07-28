package com.springboot.demo.controller;

import com.springboot.demo.model.User;
import com.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UserController {

    @GetMapping(value = "/user")
    public String printUserInfo(Model model) {
        model.addAttribute("user", (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        return "/user";
    }

}