package com.greenbridge.controllers;


import com.greenbridge.entities.Prodotto;
import com.greenbridge.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;


@RestController

public class RicercaController {
    @Autowired
    private ProdottoService prodottoService;


    @GetMapping("/risultato")
    public List<Prodotto> getProduct(@RequestParam(name = "nome", required = false) String nome) {
        if (nome == null || nome.isEmpty()) {
            return Collections.emptyList(); // O gestisci il caso in cui il nome non è presente nella richiesta
        }

        return prodottoService.getResult(nome);
    }




}

