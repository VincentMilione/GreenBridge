package com.greenbridge.controllers;

import com.greenbridge.entities.*;

import com.greenbridge.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
public class CheckoutController {
    @Autowired
    private ProdottiOrdineService prodottiOrdineService;
    @Autowired
    private OrdineService ordineService;

    @Autowired
    private IndirizzoSpedizioneService indirizzoSpedizioneService;
    @PostMapping ("/checkout")
    String getCheckout(@RequestParam int id, Model model, HttpSession session){
        Cliente cliente =(Cliente) session.getAttribute("cliente");
        List_Cart carrello = (List_Cart) session.getAttribute("list_cart");
        if(id==0){
                session.setAttribute("checkout",carrello);
        }else{
            List_Cart lista_checkout= new List_Cart(cliente);
            lista_checkout.addCart(carrello.getProdottoById(id));
            session.setAttribute("checkout",lista_checkout);
        }
        model.addAttribute("nome",cliente.getNome());
        model.addAttribute("cognome",cliente.getCognome());
        return "/checkout";
    }

    @PostMapping ("/checkForm")
    @ResponseBody
    String checkForm(@RequestBody IndirizzoSpedizione indirizzoSpedizione, Model model, HttpSession session){
        indirizzoSpedizione.setCliente( (Cliente) session.getAttribute("cliente") );
        if(!indirizzoSpedizione.isEmpty())
            indirizzoSpedizioneService.saveIndirizzoSpedizione(indirizzoSpedizione);
        List_Cart lista_checkout= (List_Cart)session.getAttribute("checkout");
        System.out.println(lista_checkout);

        Ordine ordine = new Ordine(lista_checkout.getTotale(), "mastercard", lista_checkout.getCliente(), lista_checkout.getList_cart().get(0).getProdotto().getAgricoltore());
        Ordine newOrdine=ordineService.salvaOrdine(ordine);


        for(CarrelloCliente carrelloCliente : lista_checkout.getList_cart()){
            ProdottiOrdine prodottiOrdine=new ProdottiOrdine(carrelloCliente.getProdotto(),newOrdine,carrelloCliente.getKg_richiesti());
            System.out.println(prodottiOrdine);
            prodottiOrdineService.saveProdottiOrdine(prodottiOrdine);
        }

        return "indirizzoSpedizione";
    }
}
