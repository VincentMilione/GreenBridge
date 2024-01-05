package com.greenbridge.controllers;


import com.greenbridge.entities.Prodotto;
import com.greenbridge.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController

public class RicercaController {
    @Autowired
    private ProdottoService prodottoService;


    @GetMapping("/risultato")
    List<Prodotto> getProduct( @RequestParam(name = "nome", required = false) String nome){
        System.out.println(nome);
        List<Prodotto> prodottiricerca = prodottoService.getResult(nome);
        return prodottiricerca;
    }
}

