package com.greenbridge.controllers;
import com.greenbridge.entities.Agricoltore;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.greenbridge.services.AgricoltoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class AgricoltoreController {


    @Autowired
    private AgricoltoreServiceImpl agricoltoreService;

    @GetMapping("/pageAgricoltore")
    public String homeViewAgricoltore(Model model){
        model.addAttribute("", agricoltoreService.getAgricoltori());
        return "/page";
    }
    @GetMapping("/registrazione-Agricoltore")
    public String registrazione(Model model) {
        return "RegistrazioneUtente";
    }
    @GetMapping("/modifica")
    public String modificaUtente(Model model, HttpSession session) {
        model.addAttribute("agricoltori",agricoltoreService.getAgricoltori());
        return "modify";
    }

    @GetMapping("/registratoConSuccesso")
    public String registratoConSuccesso(){
        return "RegistrazioneConSuccesso";
    }

    @GetMapping("/loginAgricoltore")
    public String loginAgricoltore(Model model){
        return"loginAgricoltore";
    }

    @GetMapping("/detailAgricoltore")
    public String DetailAgricoltore(Model model,HttpSession session){
        model.addAttribute("agricoltore",(Agricoltore)session.getAttribute("agricoltore"));
      return"detailAgricoltore";
    }

}
