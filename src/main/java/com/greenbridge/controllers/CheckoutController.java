package com.greenbridge.controllers;

import com.greenbridge.entities.Cliente;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {

    @GetMapping("/checkout")
    public String showCheckoutForm(HttpSession session, Model model){
        Cliente c=(Cliente) session.getAttribute("cliente");
        String nome=c.getNome();
        String cognome=c.getCognome();

        model.addAttribute("nome", nome);
        model.addAttribute("cognome", cognome);
        //model.addAttribute("bottega", nomeBottega);

        return "/checkout";
    }
}
