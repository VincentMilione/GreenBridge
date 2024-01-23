package com.greenbridge.controllers;


import com.greenbridge.entities.Prodotto;
import com.greenbridge.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * Controller REST che gestisce le operazioni di ricerca dei prodotti.
 *
 * @RestController Indica che le risposte dei metodi saranno serializzate direttamente in formato JSON.
 * @author Giuseppe Di Sarno
 */
@RestController

public class RicercaController {
    /**
     * Servizio per la gestione dei prodotti.
     */
    @Autowired
    private ProdottoService prodottoService;

    /**
     * Gestisce la richiesta per ottenere i risultati di ricerca dei prodotti in base al nome.
     *
     * @param nome Nome di ciò che si vuole cercare.
     * @return Lista di prodotti che corrispondono alla ricerca.
     */
    @GetMapping("/risultato")
    public List<Prodotto> getProduct(@RequestParam(name = "nome",
            required = false) String nome) {
        if (nome == null || nome.isEmpty()) {
            return Collections.emptyList();
            /* O gestisci il caso in cui il nome non
             è presente nella richiesta*/
        }

        return prodottoService.getResult(nome);
    }




}

