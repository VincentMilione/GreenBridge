package com.greenbridge.controllers;

import com.greenbridge.entities.Cliente;
import com.greenbridge.services.ClienteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  Questa classe gestisce il login e il logout
 *  dell'attore cliente sulla piattaforma
 *  @author Michele Martino
 */
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    /**
     * Attributo che permette di utilizzare
     * i servizi disponibili per il cliente
     */
    @Autowired
    private ClienteService clienteService;

    /** il metodo effettua il salvataggio
     *  di un nuovo utente nel database.
     *  Risponde all'url /saveCliente
     *  @param cliente cliente da salvare nel database
     *  @param session sessione attiva
     *  @return esito del salvataggio cliente
     */
    @PostMapping("/saveCliente")
    public ResponseEntity<String> saveCliente(
            @RequestBody Cliente cliente, HttpSession session) {
        if (clienteService.clienteExistsByEmail(cliente)) {
            return new ResponseEntity<>("notok", HttpStatus.FORBIDDEN);
        }
        clienteService.saveCliente(cliente);
        session.setAttribute("cliente", cliente);
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }
}