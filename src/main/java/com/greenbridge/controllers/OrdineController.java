package com.greenbridge.controllers;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Cliente;
import com.greenbridge.entities.Ordine;
import com.greenbridge.entities.ProdottiOrdine;
import com.greenbridge.services.OrdineService;
import com.greenbridge.services.ProdottiOrdineService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Controller che gestisce le operazioni relative agli ordini.
 *
 * @author Giuseppe Di Sarno
 */
@Controller
public class OrdineController {
    /**
     * Servizio per la gestione dei prodotti presenti nell'ordine
     */
    private final ProdottiOrdineService prodottiOrdineService;
    /**
     * Servizio per la gestione  degli ordini
     */
    private final OrdineService ordineService;

    /**
     * Costruttore del controller che prende i servizi necessari per le operazioni.
     *
     * @param ordineService
     * @param prodottiOrdineService
     */
    public OrdineController(final OrdineService ordineService,
                            ProdottiOrdineService prodottiOrdineService) {
        this.ordineService = ordineService;
        this.prodottiOrdineService = prodottiOrdineService;
    }

    /**
     * Gestisce la richiesta di visualizzazione degli ordini ricevuti per l'agricoltore in sessione.
     *
     * @param model Modello per la gestione degli attributi nella vista.
     * @param session Sessione HTTP per ottenere l'agricoltore corrente.
     * @return Stringa che rappresenta il nome della vista da visualizzare; nel caso di successo ordiniRicevuti.html, nel caso contrario loginAgricoltore.html.
     */
    @GetMapping("/ordiniAgricoltore")
    public String visualizzaOrdiniAgricoltore(final Model model,
                                              final HttpSession session) {
        Agricoltore agricoltore = (Agricoltore)
                session.getAttribute("agricoltore");
        if (agricoltore != null) {
            List<Ordine> ordini = ordineService.getOrdiniByAgricoltore(agricoltore);
            model.addAttribute("ordini", ordini);



            return "ordiniRicevuti";
        } else {
            return "loginAgricoltore";
        }
    }

    /**
     * Gestisce la richiesta di visualizzazione dettagliata dell'ordine.
     *
     * @param id Identificativo dell'ordine da visualizzare.
     * @param model Modello per la gestione degli attributi nella vista.
     * @return Stringa che rappresenta il nome della vista da visualizzare ovvero dettagliOrdine.html.
     */
    @GetMapping("/visualizzaOrdine/{id}")
    public String visualizzaOrdine(@PathVariable int id, Model model) {
        Ordine ordine = ordineService.getOrdineById(id);
        model.addAttribute("ordine", ordine);
        List<ProdottiOrdine> listOrdine =
                prodottiOrdineService.findAllProdottiOrdineByOrdine(ordine);
        model.addAttribute("listOrdine", listOrdine);
        return "dettagliOrdine";
    }


    /**
     * Gestisce la richiesta di aggiornamento dello stato di un ordine ogni qual volta l'agricoltore deciderà di cambiare stato.
     *
     * @param id Identificativo dell'ordine da aggiornare.
     * @return ResponseEntity con un messaggio di successo o errore.
     */
    @PostMapping("/aggiornaStatoOrdine/{id}")
    public ResponseEntity<String> aggiornaStatoOrdine(@PathVariable int id) {
        Ordine ordine = ordineService.getOrdineById(id);
        int stato = ordine.getStato();
        int numb  = 4;
        if(stato < numb) {
            stato++;
        }
        ordine.setStato(stato);
        ordineService.salvaOrdine(ordine);

        return ResponseEntity.ok("Stato dell'ordine aggiornato con successo");
    }

    /**
     * Gestisce la richiesta di visualizzazione degli ordini effettuati dal cliente in sessione.
     *
     * @param model Modello per la gestione degli attributi nella vista.
     * @param session Sessione HTTP per ottenere l'agricoltore corrente.
     * @return Stringa che rappresenta il nome della vista da visualizzare; nel caso di successo ordiniRicevuti.html, nel caso contrario loginAgricoltore.html.
     */
    @GetMapping("/ordiniCliente")
    public String visualizzaOrdiniCliente(final Model model,
                                              final HttpSession session) {
        Cliente cliente = (Cliente)
                session.getAttribute("cliente");
        if (cliente != null) {
            List<Ordine> ordini = ordineService.getOrdiniByCliente(cliente);
            model.addAttribute("ordini", ordini);



            return "ordiniRicevuti";
        } else {
            return "login";
        }
    }




}
