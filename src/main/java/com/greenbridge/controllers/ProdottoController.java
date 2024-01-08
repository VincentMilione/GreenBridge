package com.greenbridge.controllers;



import com.greenbridge.entities.Prodotto;
import com.greenbridge.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProdottoController{
    @Autowired
    private ProdottoService prodottoService;


   @GetMapping("/")
   public String getHome(){
        return "pages/user/home";
    }

    @GetMapping("/prodotto/{idProdotto}")
    String getProdotto(Model model, @PathVariable int idProdotto){
        Prodotto prodotto = prodottoService.getProdottoById(idProdotto);
        System.out.println(idProdotto);
        model.addAttribute("prodotto", prodotto);
        return "pages/user/prodotto";
    }

}

