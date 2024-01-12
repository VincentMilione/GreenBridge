package com.greenbridge.controllers;

import com.greenbridge.entities.*;

import com.greenbridge.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
        return "checkout";
    }

    @PostMapping ("/checkForm")
    String checkForm(@RequestBody IndirizzoSpedizione indirizzoSpedizione, Model model, HttpSession session){
        int idSpedizione=0;
        indirizzoSpedizione.setCliente( (Cliente) session.getAttribute("cliente") );
        IndirizzoSpedizione indirizzoSpedizione1;
        if(!indirizzoSpedizione.isEmpty()){
            indirizzoSpedizione1=indirizzoSpedizioneService.saveIndirizzoSpedizione(indirizzoSpedizione);
            idSpedizione=indirizzoSpedizione1.getId();
        }
        List_Cart lista_checkout= (List_Cart)session.getAttribute("checkout");
        System.out.println(lista_checkout);


        if(lista_checkout.getList_cart().size()==1) {
            Ordine ordine = new Ordine(lista_checkout.getTotale(), "mastercard", lista_checkout.getCliente(), lista_checkout.getList_cart().get(0).getProdotto().getAgricoltore()/*,idSpedizione*/);
            Ordine newOrdine = ordineService.salvaOrdine(ordine);
            prodottiOrdineService.saveAllProdottiPerOrdine(lista_checkout,ordine);
            lista_checkout.getList_cart().remove(0);

        }else  if(lista_checkout.getList_cart().size()!=0 ){
            System.out.println("la lunghezza lista è "+lista_checkout.getList_cart().size());
            List_Cart lista_agricoltore=new List_Cart(lista_checkout.getCliente());
                int idAgricoltore =lista_checkout.getList_cart().get(0).getProdotto().getAgricoltore().getId();
                int i=0;
                while(lista_checkout.getList_cart().get(i).getProdotto().getAgricoltore().getId()==idAgricoltore && i<lista_checkout.getList_cart().size()){
                        lista_agricoltore.addCart(lista_checkout.getList_cart().get(i));
                        lista_checkout.getList_cart().remove(i);

                }
                Ordine ordine = new Ordine(lista_agricoltore.getTotale(), "mastercard", lista_agricoltore.getCliente(), lista_agricoltore.getList_cart().get(0).getProdotto().getAgricoltore()/*,idSpedizione*/);
                Ordine newOrdine = ordineService.salvaOrdine(ordine);
                prodottiOrdineService.saveAllProdottiPerOrdine(lista_agricoltore,ordine);
        }

        if(lista_checkout.getList_cart().size()==0){
            System.out.println(lista_checkout.getList_cart());
            return "paymentSuccess";

        }else{
            System.out.println("la nuova lista è"+lista_checkout.getList_cart());
            session.removeAttribute("checkout");
            session.setAttribute("checkout",lista_checkout);
            return "checkout" ;


        }
    }
}
