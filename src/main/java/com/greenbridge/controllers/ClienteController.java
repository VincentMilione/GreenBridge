package com.greenbridge.controllers;


import com.greenbridge.services.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/** La classe è un controller che gestisce il redirect
 *  alle pagine di interesse per l'attore cliente.
 *  @author Michele Martino
 * */
@Controller
public class ClienteController {

    /**
     * Permette di utilizzare i servizi disposti per l'entità cliente.
     */
    @Autowired
    private ClienteServiceImpl clienteService;


    /** il metodo effettua il redirect alla pagina di registrazione cliente.
     *  Risponde all'url /register
     * @return nome del template HTML
     * */
    @GetMapping("/register")
    public String registraCliente() {
        return "registrazioneCliente";
    }

    /** il metodo effettua il redirect alla homepage cliente.
     *  Risponde all'url /home
     *  @param model contiene gli attributi da visualizzare
     *              nella view di ritorno
     *  @return nome del template HTML
     * */
    @GetMapping("/")
    public String homeView(Model model) {
        model.addAttribute("clienti", clienteService.getAllClienti());
        return "home";
    }

    /** il metodo effettua il redirect alla pagina dettagli cliente.
     *  Risponde all'url /detailCliente
     *  @return nome del template HTML
     * */
    @GetMapping("/detailCliente")
    public String detailClienteView() {
        return "detailCliente";
    }

    /** il metodo effettua il redirect alla pagina di login cliente.
     *  Risponde all'url /login
     *  @return nome del template HTML
     * */
    @GetMapping("/login")
    public String loginClienteView() {
        return "loginCliente";
    }

}
