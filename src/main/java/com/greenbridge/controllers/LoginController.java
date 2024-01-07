package com.greenbridge.controllers;

import com.greenbridge.entities.Cliente;
import com.greenbridge.services.ClienteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

/** Questa classe gestisce le operazione di autenticazione nel sistema.*/
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private ClienteService clienteService;

    /**Questo metodo autentifica un utente cliente.*/
    @PostMapping("/loginCliente")
    public ResponseEntity<String> saveCliente(
            @RequestBody Cliente cliente,
            HttpSession session) {
        Cliente c = clienteService.getClienteByEmail(cliente.getEmail());
        if (c != null &&
            cliente.getPassword().compareTo(c.getPassword()) == 0) {
            session.setAttribute("cliente", c);
            return new ResponseEntity<>("ok",HttpStatus.OK);
        }
        return new ResponseEntity<>("notok", HttpStatus.FORBIDDEN);
    }

    /**Questo metodo effettua il logout di un utente cliente.*/
    @GetMapping("/logoutCliente")
    public RedirectView logoutCliente(HttpSession session) {
        session.removeAttribute("cliente");
        return new RedirectView("/home");
    }

}