package com.greenbridge.controllers;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Prodotto;
import com.greenbridge.entities.RecensioneProdotti;
import com.greenbridge.services.ProdottoService;
import com.greenbridge.services.RecensioneService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * Controller per la gestione dei prodotti.
 * Autore:Mauro
 */
@Controller
public class ProdottoController {
    /**
     * Service del prodotto.
     */
    @Autowired
    private ProdottoService prodottoService;
    /**
     * Service per le recensioni del prodotto.
     */
    @Autowired
    private RecensioneService recensioneService;

    /**
     * Mostra il form per l'inserimento di un nuovo prodotto.
     * @param model   Modello per la gestione degli attributi nella vista.
     * @param session Sessione HTTP.
     * @return Nome della vista da visualizzare.
     */
    @GetMapping("/formInserimento")
    public String getProdotto(final Model model, final HttpSession session) {
        if (session.getAttribute("agricoltore") != null) {
            model.addAttribute("prodotto", new Prodotto());
            return "pages/user/formInserimento";
        }
        return "loginAgricoltore";
    }

    /**
     * Aggiunge un nuovo prodotto al sistema.
     *
     * @param prodotto       Oggetto Prodotto da aggiungere.
     * @param immagineFile   Immagine associata al prodotto.
     * @param model          Modello per la gestione
     *                       degli attributi nella vista.
     * @param session        Sessione HTTP.
     * @return Nome della vista da visualizzare.
     */
    @PostMapping("/addProdotto")
    public String addProdottoForm(@ModelAttribute("prodotto") final Prodotto
              prodotto, @RequestParam("immagineFile")
                       final MultipartFile immagineFile,
                       final Model model, final HttpSession session) {
        try {
            if (!immagineFile.isEmpty()) {
                byte[] bytes = immagineFile.getBytes();
                prodotto.setImmagine(bytes);
            }
            Agricoltore agricoltore = (Agricoltore)
                    session.getAttribute("agricoltore");
            prodotto.setAgricoltore(agricoltore);

            prodotto.setAcquistabile(true);

            prodottoService.saveProdotto(prodotto);

            // Aggiorna la lista di prodotti nel model
            List<Prodotto> prodotti = prodottoService.
                    getAllProdotti((Agricoltore)
                    session.getAttribute("agricoltore"));
            for (Prodotto p : prodotti) {
                if (p.getImmagine() != null) {
                    String immagineBase64 = Base64.getEncoder().
                            encodeToString(p.getImmagine());
                    p.setImmagineBase64(immagineBase64);
                }
            }
            model.addAttribute("prodotti", prodotti);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            // Forza il lancio dell'eccezione
            throw new DataIntegrityViolationException
                    ("Data truncation: Data too long for column");
        } catch (Exception e) {
            handleException(e);
        }
        return "pages/user/catalogo";
    }


    /**
     * Gestisce l'eccezione di violazione dell'integrità dei dati.
     *
     * @param e Eccezione di violazione dell'integrità dei dati.
     */
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handleDataIntegrityViolationException
    (final DataIntegrityViolationException e) {
        System.out.println("Handling DataIntegrityViolationException: "
                + e.getMessage());
    }

    /**
     * Gestisce un'eccezione generica.
     *
     * @param e Eccezione generica.
     */
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(Exception.class)
    public void handleException(final Exception e) {
        System.out.println("Handling Exception: " + e.getMessage());
    }

    /**
     * Mostra il catalogo dei prodotti.
     *
     * @param model   Modello per la gestione degli attributi nella vista.
     * @param session Sessione HTTP.
     * @return Nome della vista da visualizzare.
     */
    @GetMapping("/catalogo")
    public String getCatalogo(final Model model,
                              final HttpSession session) {
        if (session.getAttribute("agricoltore") == null) {
            return "loginAgricoltore";
        }

        List<Prodotto> prodotti =
                prodottoService.getAllProdotti((Agricoltore)
                session.getAttribute("agricoltore"));

        for (Prodotto prodotto : prodotti) {
            if (prodotto.getImmagine() != null) {
                String immagineBase64 = Base64.getEncoder().
                        encodeToString(prodotto.getImmagine());
                prodotto.setImmagineBase64(immagineBase64);
            }
        }
        model.addAttribute("prodotti", prodotti);
        return "pages/user/catalogo";
    }

    /**
     * Mostra il form per la modifica di un prodotto.
     *
     * @param id     ID del prodotto da modificare.
     * @param model  Modello per la gestione degli attributi nella vista.
     * @param session Sessione HTTP.
     * @return Nome della vista da visualizzare.
     */
    @GetMapping("/formModificaProdotto/{id}")
    public String formModificaProdotto(@PathVariable("id") final int id,
                                       final Model model,
                                       final HttpSession session) {
        if (session.getAttribute("agricoltore") == null) {
            return "loginAgricoltore";
        }
        Prodotto prodotto = prodottoService.getProdottoById(id);
        model.addAttribute("prodottoMod", prodotto);
        return "pages/user/formModifica";
    }


    /**
     * Modifica un prodotto esistente.
     *
     * @param prodotto      Oggetto Prodotto da modificare.
     * @param immagineFile  Nuova immagine associata al prodotto.
     * @param model         Modello per la gestione degli attributi nella vista.
     * @param session       Sessione HTTP.
     * @return Nome della vista da visualizzare.
     */
    @PostMapping("/modProdotto")
    public String modificaProdottoForm(@ModelAttribute
                   ("prodottoMod") final Prodotto prodotto,
                   @RequestParam("immagineFile")
                   final MultipartFile immagineFile,
                   final Model model, final HttpSession session) {
        try {
            if (!immagineFile.isEmpty()) {
                byte[] bytes = immagineFile.getBytes();
                prodotto.setImmagine(bytes);
            }

            prodotto.setAgricoltore((Agricoltore) session.
                    getAttribute("agricoltore"));
            prodotto.setAcquistabile(true);

            prodottoService.saveAndFlushProdotto(prodotto);

            // Aggiorna la lista di prodotti nel model
            List<Prodotto> prodotti = prodottoService.
                    getAllProdotti((Agricoltore) session.
                            getAttribute("agricoltore"));
            for (Prodotto p : prodotti) {
                if (p.getImmagine() != null) {
                    String immagineBase64 = Base64.
                            getEncoder().encodeToString(p.getImmagine());
                    p.setImmagineBase64(immagineBase64);
                }
            }
            model.addAttribute("prodotti", prodotti);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "pages/user/catalogo";
    }

    /**
     * Cancella un prodotto esistente.
     *
     * @param session Sessione HTTP.
     * @param model   Modello per la gestione
     *                degli attributi nella vista.
     * @param id      ID del prodotto da cancellare.
     * @return Nome della vista da visualizzare.
     */
    @GetMapping("/cancellaProdotto/{id}")
    public String cancellaProdotto(final HttpSession session,
                   final Model model, @PathVariable("id")
                                       final int id) {
        Prodotto prodotto = prodottoService.getProdottoById(id);
        prodotto.setAcquistabile(false);
        prodottoService.saveAndFlushProdotto(prodotto);

        List<Prodotto> prodotti =
                prodottoService.getAllProdotti((Agricoltore)
                session.getAttribute("agricoltore"));
        for (Prodotto p : prodotti) {
            if (p.getImmagine() != null) {
                String immagineBase64 = Base64.getEncoder().
                        encodeToString(p.getImmagine());
                p.setImmagineBase64(immagineBase64);
            }
        }
        model.addAttribute("prodotti", prodotti);

        return "pages/user/catalogo";
    }

    /**
     * Gestisce la richiesta di visualizzazione
     * di un singolo prodotto identificato dall'id associato.
     *
     * @param model Modello per la gestione
     *              degli attributi nella vista.
     * @param idProdotto Identificativo del
     *                   prodotto da visualizzare.
     * @return Stringa che rappresenta il nome
     *         della vista da visualizzare ovvero prodotto.html
     */
    @GetMapping("/prodotto/{idProdotto}")
    String getProdotto(final Model model, @PathVariable final int idProdotto) {
        Prodotto prodotto = prodottoService.getProdottoById(idProdotto);
        model.addAttribute("prodotto", prodotto);
        List<RecensioneProdotti> recensioni = recensioneService.
                getRecensioniByIdProdotto(prodotto.getIdProdotto());
        model.addAttribute("recensioni", recensioni);
        return "prodotto";
    }
    /**
     * Redirect alla home.
     */
    @GetMapping("/")
    public String getHome() {
        return "pages/user/home";
    }


    /**
     * Gestisce la richiesta di ricerca dei prodotti in base al nome.
     *
     * @param name Nome del prodotto da cercare
     * @param model Modello per la gestione degli attributi nella vista.
     * @return Stringa che rappresenta il nome della vista da visualizzare;
     * nel caso di successo ricercaProdotto.html,
     * nel caso contrario errrore.html
     */
    @PostMapping ("/ricerca")
    public String getProduct(@RequestParam final String name,
                             final Model model) {
        int numb = 50;
        if (name.trim().isEmpty() || name.length() > numb
                || !name.matches("^[A-Za-zÀ-ù ‘-]{1,50}$")) {
            return "error.html";
        }

        List<Prodotto> risultatiRicerca = prodottoService.getResult(name);
        if (!risultatiRicerca.isEmpty()) {
            model.addAttribute("ricerca", risultatiRicerca);
        }
        return "pages/user/ricercaProdotto";
    }

}

