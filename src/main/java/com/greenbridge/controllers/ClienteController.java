package com.greenbridge.controllers;

import com.greenbridge.entities.Cliente;
import com.greenbridge.services.ClienteService;
import com.greenbridge.services.ClienteServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/register")
    public String registraCliente(Model model){

        return "registrazioneCliente";
    }

    @GetMapping("/home")
    public String homeView(Model model){
        model.addAttribute("clienti", clienteService.getAllClienti());
        return "home";
    }

    @GetMapping("/detailCliente")
    public String detailClienteView(HttpSession session){
        return "detailCliente";
    }
    @GetMapping("/login")
    public String loginClienteView(Model model){
        return "loginCliente";
    }
}