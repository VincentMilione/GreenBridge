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



    @GetMapping("/carrello")
        //@ResponseBody
    String getCarrello(Model model, HttpSession session){
        List_Cart list_cart = (List_Cart)session.getAttribute("list_cart");

        model.addAttribute("cart",list_cart.getProdottiOrdinati());
        model.addAttribute("totale", list_cart.getTotale());
        return "/carrello";

    }





}
