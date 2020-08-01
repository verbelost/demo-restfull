package com.springboot.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class ViewController {

    @GetMapping(value = "/admin")
    public String printAdminPage() {
        return "admin";
    }

    @GetMapping(value = "/user")
    public String printUserPage() {
        return "user";
    }
}