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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

@Controller
public class RecensioneController {

    @Autowired
    private ProdottiOrdineService prodottiOrdineService;
    @Autowired
    private RecensioneService recensioneService;
    @Autowired
    private OrdineService ordineService;
    private static final int MINVOTO = 1;
    private static final int MAXVOTO = 5;
    private static final int MAXDESCRIZIONE = 200;
    @GetMapping("/recensione/{id}")
    public String recensione(
            @PathVariable("id") Integer id, HttpSession session, Model model) {
        if (session.getAttribute("cliente") == null) {
            return "loginCliente";
        } else {
            Ordine ordine = ordineService.getOrdineById(id);
            List<ProdottiOrdine> prodottiDaRecensire = prodottiOrdineService.
                    findAllProdottiOrdineByOrdineId(ordine);
            session.setAttribute("prodottiOrdine", prodottiDaRecensire);
            session.setAttribute("indiceRecensioni", 0);
            int indice = (int) session.getAttribute("indiceRecensioni");
            model.addAttribute(
                    "prodottoDaRecensire", prodottiDaRecensire.get(0));
            model.addAttribute("recensione", new RecensioneProdotti());
            return "recensioneProdotto";
        }
    }


    @PostMapping("/saveRecensione")
    public String saveRecensione(
            @ModelAttribute("rec") RecensioneProdotti rec,
            HttpSession session, Model model) throws Exception {
        if ((rec.getVoto() < MINVOTO || rec.getVoto() > MAXVOTO)) {
            throw new Exception("voto non corretto");
        }

        if (rec.getDescrizione().length() > MAXDESCRIZIONE) {
            throw new Exception("descrizione troppo lunga");
        }
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        Integer idCliente = cliente.getId();
        List<ProdottiOrdine> prodottiOrdine = (List<ProdottiOrdine>)
                session.getAttribute("prodottiOrdine");
        rec.setIdCliente(idCliente);
        int indice = (int) session.getAttribute("indiceRecensioni");
        rec.setIdProdotto(prodottiOrdine.get(indice).getId());
        recensioneService.saveRecensioneProdotto(rec);
        indice++;
        if (indice == prodottiOrdine.size()) {
            return "home";
        } else {
            model.addAttribute("prodottoDaRecensire",
                    prodottiOrdine.get(indice));
            model.addAttribute("recensione",
                    new RecensioneProdotti());
            session.setAttribute("indiceRecensioni", indice);
            return "recensioneProdotto";
        }
    }
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        System.out.println("Handling DataIntegrityViolationException: "
                + e.getMessage());
    }

    @PostMapping("/recensioneAvanti")
    String avanti(HttpSession session, Model model) {
        List<ProdottiOrdine> prodottiOrdine =
                (List<ProdottiOrdine>) session.getAttribute("prodottiOrdine");
        int indice = (int) session.getAttribute("indiceRecensioni");
        indice++;
        System.out.println("avanti " + indice);
        session.setAttribute("indiceRecensioni", indice);
        model.addAttribute("prodottoDaRecensire", prodottiOrdine.get(indice));
        model.addAttribute("recensione", new RecensioneProdotti());
        return "recensioneProdotto";
    }

    @PostMapping("/recensioneIndietro")
    String indietro(HttpSession session, Model model) {
        List<ProdottiOrdine> prodottiOrdine =
                (List<ProdottiOrdine>) session.getAttribute("prodottiOrdine");
        int indice = (int) session.getAttribute("indiceRecensioni");
        indice--;
        System.out.println("indietro " + indice);
        session.setAttribute("indiceRecensioni", indice);
        model.addAttribute("prodottoDaRecensire", prodottiOrdine.get(indice));
        model.addAttribute("recensione", new RecensioneProdotti());
        return "recensioneProdotto";
    }
}
