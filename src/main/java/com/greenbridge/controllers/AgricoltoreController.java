package com.greenbridge.controllers;
import com.greenbridge.entities.Agricoltore;
import org.springframework.ui.Model;
import com.greenbridge.services.AgricoltoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class AgricoltoreController {

    @GetMapping("/registrazione-Agricoltore")
    public String registrazione(Model model) {

        return "pages/user/RegistrazioneUtente";
    }
    @GetMapping("/modifica")
    public String modificaUtente() {
        return "pages/user/modify";
    }



}
