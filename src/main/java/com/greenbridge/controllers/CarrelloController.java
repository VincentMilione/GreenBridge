package com.greenbridge.controllers;

import com.greenbridge.entities.ListCart;
import com.greenbridge.services.CarrelloClienteService;
import com.greenbridge.services.ClienteServiceImpl;
import com.greenbridge.services.IndirizzoSpedizioneService;
import com.greenbridge.services.ProdottoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
/**
 * Controller per gestire le operazioni del carrello degli acquisti.
 */

@Controller
public class CarrelloController {
    /**
     * Servizio per la gestione delle
     * operazioni legate ai prodotti.
     */
    @Autowired
    private ProdottoService prodottoService;
    /**
     * Servizio per la gestione delle
     * operazioni legate ai clienti.
     */
    @Autowired
    private ClienteServiceImpl clienteService;
    /**
     * Servizio per la gestione delle
     * operazioni legate al carrello.
     */
    @Autowired
    private CarrelloClienteService carrelloClienteService;
    /**
     * Servizio per la gestione delle
     * operazioni legate agli indirizzi stradali.
     */
    @Autowired
    private IndirizzoSpedizioneService indirizzoSpedizioneService;


    /**
     * Metodo per gestire la richiesta GET a "/carrello".
     *
     * @param model   Modello per aggiungere attributi per la visualizzazione.
     * @param session Sessione HTTP per mantenere lo stato del carrello.
     * @return La vista associata al carrello.
     *  * @Author salvatore mattiello.
     */
    @GetMapping("/carrello")
    //@ResponseBody
    String getCarrello(Model model, HttpSession session) {
        ListCart listCart = (ListCart) session.getAttribute("list_cart");
        model.addAttribute("cart", listCart.getProdottiOrdinati());
        model.addAttribute("totale", listCart.getTotale());
        return "/carrello";

    }





}