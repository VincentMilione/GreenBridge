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

/**
 *  Questa classe gestisce le recensioni
 *  sui prodotti ad opera dei clienti.
 *  @author Michele Martino
 */
@Controller
public class RecensioneController {
    /**
     * Attributo che permette di utilizzare
     * i servizi disponibili per prodotti legati
     * ad un ordine.
     */
    @Autowired
    private ProdottiOrdineService prodottiOrdineService;
    /**
     * Attributo che permette di utilizzare
     * i servizi disponibili per le
     * recensioni prodotto.
     */
    @Autowired
    private RecensioneService recensioneService;
    /**
     * Attributo che permette di utilizzare
     * i servizi disponibili per gli ordini.
     */
    @Autowired
    private OrdineService ordineService;
    /**
     * indica il voto minimo accettato per una recensione.
     */
    private static final int MINVOTO = 1;
    /**
     * indica il voto massimo accettato per una recensione.
     */
    private static final int MAXVOTO = 5;
    /**
     * indica la lunghezza massima della descrizione
     * di una recensione.
     */
    private static final int MAXDESCRIZIONE = 200;

    /**
     * il metodo permette di accedere al form
     * di recensione per i prodotti di un ordine.
     * @param id id dell'ordine di riferimento
     * @param session sessione attiva da cui attingere ai dati cliente
     * @param model contiene gli attributi da visualizzare nella view
     * @return nome del template html verso cui essere rendirizzati
     */
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

    /**
     * Il metodo permette di salvare una recensione per un prodotto.
     * @param rec contiene il voto e la descrzione della recensione
     * @param session sessione attiva da cui prendere i dati utente
     * @param model contiene gli attributi da visualizzare nella view
     * @return nome del template html verso cui essere rendirizzati
     * @throws Exception viene generata se il voto o la descrizione
     * non sono conformi
     */
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
        rec.setIdProdotto(prodottiOrdine.get(indice).getProdotto().getIdProdotto());
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

    /**
     * permette di associare alle eccezioni
     * degli HttpStatus.
     * @param e eccezione generata
     */
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        System.out.println("Handling DataIntegrityViolationException: "
                + e.getMessage());
    }

    /**
     * Il metodo consente di saltare una recensione
     * e passare alla recensione successiva
     * se l'ordine contiene più di un prodotto.
     * @param session sessione attiva
     * @param model contiene gli attributi da visualizzare nella view
     * @return nome del template html verso cui essere rendirizzati
     */
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

    /**
     * Il metodo consente di tornare ad una recensione
     * precendente
     * se l'ordine contiene più di un prodotto.
     * @param session sessione attiva
     * @param model contiene gli attributi da visualizzare nella view
     * @return nome del template html verso cui essere rendirizzati
     */
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
