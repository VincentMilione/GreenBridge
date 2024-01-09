package com.greenbridge.controllers;

import com.greenbridge.entities.CarrelloCliente;
import com.greenbridge.entities.List_Cart;
import com.greenbridge.entities.Prodotto;
import com.greenbridge.services.CarrelloClienteService;
import com.greenbridge.services.ClienteServiceImpl;
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
    @PostMapping ("/checkout")
        @ResponseBody
    String getCheckout(@RequestParam int id, Model model, HttpSession session){
            if(id==0){ //checkkout di tutti i prodotti

            }else{ //checkout di un unico prodotto

            }

        return "stai facnedo il checkout del prodotto con id" + id;

    }




}
