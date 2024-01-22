package com.greenbridge.controllers;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Ordine;
import com.greenbridge.entities.ProdottiOrdine;
import com.greenbridge.services.OrdineService;
import com.greenbridge.services.ProdottiOrdineService;
import com.greenbridge.services.ProdottoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class OrdineController {
    private ProdottiOrdineService prodottiOrdineService;
    private final OrdineService ordineService;
    public OrdineController(OrdineService ordineService, ProdottiOrdineService prodottiOrdineService) {
        this.ordineService = ordineService;
        this.prodottiOrdineService = prodottiOrdineService;
    }

    @GetMapping("/ordiniAgricoltore")
    public String visualizzaOrdiniAgricoltore(Model model, HttpSession session) {
        Agricoltore agricoltore = (Agricoltore) session.getAttribute("agricoltore");
        System.out.println(agricoltore);
        if (agricoltore != null) {
            List<Ordine> ordini = ordineService.getOrdiniByAgricoltore(agricoltore);
            model.addAttribute("ordini", ordini);



            return "ordiniRicevuti";
        } else {
            return "loginAgricoltore";
        }
    }

    @GetMapping("/visualizzaOrdine/{id}")
    public String visualizzaOrdine(@PathVariable int id, Model model) {
        Ordine ordine = ordineService.getOrdineById(id);
        model.addAttribute("ordine", ordine);
        List<ProdottiOrdine> listOrdine = prodottiOrdineService.findAllProdottiOrdineByOrdine(ordine);
        System.out.println("i prodotti di quest'ordine sono:"+listOrdine);
        model.addAttribute("listOrdine", listOrdine);
        return "dettagliOrdine";
    }

    @PostMapping("/aggiornaStatoOrdine/{id}")
    public ResponseEntity<String> aggiornaStatoOrdine(@PathVariable int id) {
        Ordine ordine = ordineService.getOrdineById(id);
        int stato = ordine.getStato();
        if(stato<4) {
            stato++;
        }
        ordine.setStato(stato);
        ordineService.salvaOrdine(ordine);

        return ResponseEntity.ok("Stato dell'ordine aggiornato con successo");
    }
}
