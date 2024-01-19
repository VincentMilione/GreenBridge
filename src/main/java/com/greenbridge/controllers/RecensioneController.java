package com.greenbridge.controllers;

import com.greenbridge.entities.Cliente;
import com.greenbridge.entities.Ordine;
import com.greenbridge.entities.ProdottiOrdine;
import com.greenbridge.entities.RecensioneProdotti;
import com.greenbridge.services.OrdineService;
import com.greenbridge.services.ProdottiOrdineService;
import com.greenbridge.services.RecensioneService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class RecensioneController {

    @Autowired
    ProdottiOrdineService prodottiOrdineService;
    @Autowired
    RecensioneService recensioneService;
    @Autowired
    OrdineService ordineService;

    @GetMapping("/recensione/{id}")
    String recensione(@PathVariable("id") Integer id, HttpSession session, Model model){
        if(session.getAttribute("cliente") == null) {
            return "loginCliente";
        } else {
            Ordine ordine = ordineService.getOrdineById(id);
            List<ProdottiOrdine> prodottiDaRecensire = prodottiOrdineService.findAllProdottiOrdineByOrdineId(ordine);
            session.setAttribute("prodottiOrdine", prodottiDaRecensire);
            session.setAttribute("indiceRecensioni", 0);
            int indice = (int) session.getAttribute("indiceRecensioni");
            model.addAttribute("prodottoDaRecensire", prodottiDaRecensire.get(0));
            model.addAttribute("recensione", new RecensioneProdotti());
            return "recensioneProdotto";
        }
    }

    @PostMapping("/saveRecensione")
    String saveRecensione(@ModelAttribute RecensioneProdotti rec, HttpSession session, Model model){

        Cliente cliente = (Cliente) session.getAttribute("cliente");
        Integer idCliente = cliente.getId();
        List<ProdottiOrdine> prodottiOrdine = (List<ProdottiOrdine>) session.getAttribute("prodottiOrdine");
        rec.setIdCliente(idCliente);
        int indice = (int) session.getAttribute("indiceRecensioni");
        rec.setIdProdotto(prodottiOrdine.get(indice).getId());
        recensioneService.saveRecensioneProdotto(rec);
        indice++;
        if (indice == prodottiOrdine.size()) {
            return "home";
        }
        else{
            model.addAttribute("prodottoDaRecensire", prodottiOrdine.get(indice));
            model.addAttribute("recensione", new RecensioneProdotti());
            session.setAttribute("indiceRecensioni", indice);
            return "recensioneProdotto";
        }
    }

    @PostMapping("/recensioneAvanti")
    String avanti(HttpSession session, Model model){
        List<ProdottiOrdine> prodottiOrdine = (List<ProdottiOrdine>) session.getAttribute("prodottiOrdine");
        int indice =(int) session.getAttribute("indiceRecensioni");
        indice++;
        System.out.println("avanti " + indice);
        session.setAttribute("indiceRecensioni", indice);
        model.addAttribute("prodottoDaRecensire", prodottiOrdine.get(indice));
        model.addAttribute("recensione", new RecensioneProdotti());
        return "recensioneProdotto";
    }

    @PostMapping("/recensioneIndietro")
    String indietro(HttpSession session, Model model){
        List<ProdottiOrdine> prodottiOrdine = (List<ProdottiOrdine>) session.getAttribute("prodottiOrdine");
        int indice =(int) session.getAttribute("indiceRecensioni");
        indice--;
        System.out.println("indietro " + indice);
        session.setAttribute("indiceRecensioni", indice);
        model.addAttribute("prodottoDaRecensire", prodottiOrdine.get(indice));
        model.addAttribute("recensione", new RecensioneProdotti());
        return "recensioneProdotto";
    }
}
