package com.greenbridge.controllers;
import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Cliente;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.greenbridge.services.AgricoltoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class AgricoltoreController {

    @Autowired
    private AgricoltoreService agricoltoreService;
    @GetMapping("/registrazione-Agricoltore")
    public String registrazione(Model model) {
        return "/RegistrazioneUtente";
    }
    @GetMapping("/modifica")
    public String modificaUtente(Model model) {
        return "/modify";
    }

    @GetMapping("/registratoConSuccesso")
    public String registratoConSuccesso(){
        return "/RegistrazioneConSuccesso";
    }



}
