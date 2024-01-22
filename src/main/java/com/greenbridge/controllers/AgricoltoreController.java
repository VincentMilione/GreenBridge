package com.greenbridge.controllers;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.greenbridge.services.AgricoltoreServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AgricoltoreController {
    private   AgricoltoreServiceImpl agricoltoreService;
    public AgricoltoreController(AgricoltoreServiceImpl agricoltoreService) {
        this.agricoltoreService = agricoltoreService;
    }
    @GetMapping("/pageAgricoltore")
    public String homeViewAgricoltore(Model model) {
        model.addAttribute("", agricoltoreService.getAgricoltori());
        return "/page";
    }
    @GetMapping("/registrazione-Agricoltore")
    public String registrazione() {
        return "RegistrazioneUtente";
    }
    @GetMapping("/modifica")
    public String modificaUtente(Model model) {
        model.addAttribute("agricoltori", agricoltoreService.getAgricoltori());
        return "modify";
    }

    @GetMapping("/registratoConSuccesso")
    public String registratoConSuccesso() {
        return "RegistrazioneConSuccesso";
    }

    @GetMapping("/loginAgricoltore")
    public String loginAgricoltore() {

        return "loginAgricoltore";
    }

    @GetMapping("/detailAgricoltore")
    public String detailAgricoltore(Model model, HttpSession session) {
        model.addAttribute("agricoltore", session.getAttribute("agricoltore"));
      return "detailAgricoltore";
    }

}
