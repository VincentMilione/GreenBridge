package com.greenbridge.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatbotController {
    @GetMapping("/chatbot")
    public String showForm(){
        return "/pages/user/form";
    }

    @GetMapping("/")
    public String showHome(){return "/pages/user/home";}
}
