package com.greenbridge.controllers;


import com.greenbridge.services.ClienteServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/** La classe è un controller che gestisce il redirect
 * alla pagine di interesse per l'attore cliente. */
@Controller
public class ClienteController {



    @Autowired
    private ClienteServiceImpl clienteService;


    /** il metodo effettua il redirect alla pagina di registrazione cliente. */
    @GetMapping("/register")
    public String registraCliente() {
        return "registrazioneCliente";
    }

    /** il metodo effettua il redirect alla pagina di home per il cliente. */
    @GetMapping("/home")
    public String homeView(Model model) {
        model.addAttribute("clienti", clienteService.getAllClienti());
        return "home";
    }

    /** il metodo effettua il redirect alla pagina dettagli cliente. */
    @GetMapping("/detailCliente")
    public String detailClienteView(HttpSession session) {
        return "detailCliente";
    }

    /** il metodo effettua il redirect alla pagina di login cliente. */
    @GetMapping("/login")
    public String loginClienteView(Model model) {
        return "loginCliente";
    }


}
