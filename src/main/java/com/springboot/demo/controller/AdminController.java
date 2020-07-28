package com.springboot.demo.controller;

import com.springboot.demo.model.User;
import com.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdminController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String printUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", this.userService.listUsers());
        model.addAttribute("userAuthentication", (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        return "admin";
    }

    @PostMapping(value = "/admin/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("select") String[] rolesValues) {
        this.userService.addUser(user, rolesValues);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        this.userService.removeUser(id);
        return "redirect:/admin";
    }
}