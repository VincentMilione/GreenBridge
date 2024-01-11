package com.greenbridge.controllers;

import com.greenbridge.entities.*;
import com.greenbridge.services.CarrelloClienteService;
import com.greenbridge.services.ClienteServiceImpl;
import com.greenbridge.services.IndirizzoSpedizioneService;
import com.greenbridge.services.ProdottoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.*;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;


@Controller
public class CarrelloController {
    @Autowired
    private ProdottoService prodottoService;
    @Autowired
    private ClienteServiceImpl clienteService;
    @Autowired
    private CarrelloClienteService carrelloClienteService;
    @Autowired
    private IndirizzoSpedizioneService indirizzoSpedizioneService;



    @GetMapping("/prodotto/{id}")
        //@ResponseBody
    String getProdotto(Model model,@PathVariable Integer id){

        Prodotto prodotto = prodottoService.getProdottoById(id);
        model.addAttribute("prodotto", prodotto);
        return "/prodotto";
    }

    @GetMapping("/carrello")
        //@ResponseBody
    String getCarrello(Model model, HttpSession session){
        List_Cart list_cart = (List_Cart)session.getAttribute("list_cart");

        model.addAttribute("cart",list_cart.getProdottiOrdinati());
        model.addAttribute("totale", list_cart.getTotale());
        return "/carrello";

    }
   /* @PostMapping ("/checkout")
    String getCheckout(@RequestParam int id, Model model, HttpSession session){
           Cliente cliente =(Cliente) session.getAttribute("cliente");
           session.setAttribute("prodotto",prodottoService.getProdottoById(id));
           model.addAttribute("nome",cliente.getNome());
           model.addAttribute("cognome",cliente.getCognome());
           return "/checkout";
    }

    @PostMapping ("/checkForm")
    @ResponseBody
    String checkForm(@RequestBody IndirizzoSpedizione indirizzoSpedizione, Model model, HttpSession session){
        indirizzoSpedizione.setCliente( (Cliente) session.getAttribute("cliente") );
        indirizzoSpedizioneService.saveIndirizzoSpedizione(indirizzoSpedizione);


        return "indirizzoSpedizione";
    }*/




}
