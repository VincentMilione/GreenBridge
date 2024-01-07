package com.greenbridge.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChatbotController {
    @GetMapping("/")
    public String showHome(Model model, HttpSession session) {
        session.setAttribute("cliente", "true");
        return "/pages/user/home";
    }
}
