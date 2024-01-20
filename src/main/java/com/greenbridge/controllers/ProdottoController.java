package com.greenbridge.controllers;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Prodotto;
import com.greenbridge.services.AgricoltoreService;
import com.greenbridge.services.ProdottoService;
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


@Controller
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @Autowired
    private AgricoltoreService agricoltoreService;


    @GetMapping("/formInserimento")
    public String getProdotto(Model model, HttpSession session) {
        if(session.getAttribute("agricoltore")!= null) {
            model.addAttribute("prodotto", new Prodotto());
            return "pages/user/formInserimento";
        }
        return "loginAgricoltore";
    }

    @PostMapping("/addProdotto")
    public String addProdottoForm(@ModelAttribute("prodotto") Prodotto prodotto,
                                  @RequestParam("immagineFile") MultipartFile immagineFile, Model model,
                                  HttpSession session) {
        try {
            if (!immagineFile.isEmpty()) {
                byte[] bytes = immagineFile.getBytes();
                prodotto.setImmagine(bytes);
            }
            Agricoltore agricoltore = (Agricoltore) session.getAttribute("agricoltore");
            prodotto.setAgricoltore(agricoltore);

            prodotto.setAcquistabile(true);

            prodottoService.saveProdotto(prodotto);

            // Aggiorna la lista di prodotti nel model
            List<Prodotto> prodotti = prodottoService.getAllProdotti((Agricoltore) session.getAttribute("agricoltore"));
            for (Prodotto p : prodotti) {
                if (p.getImmagine() != null) {
                    String immagineBase64 = Base64.getEncoder().encodeToString(p.getImmagine());
                    p.setImmagineBase64(immagineBase64);
                }
            }
            model.addAttribute("prodotti", prodotti);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            // Forza il lancio dell'eccezione
            throw new DataIntegrityViolationException("Data truncation: Data too long for column");
        } catch (Exception e){
            handleException(e);
        }
        return "pages/user/catalogo";
    }

    // Gestione dell'eccezione a livello di controller
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        // Puoi aggiungere ulteriori log o gestione dell'errore se necessario
        System.out.println("Handling DataIntegrityViolationException: " + e.getMessage());
    }
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        // Puoi aggiungere ulteriori log o gestione dell'errore se necessario
        System.out.println("Handling Exception: " + e.getMessage());
    }

    @GetMapping("/catalogo")
    public String getCatalogo(Model model, HttpSession session) {
        if(session.getAttribute("agricoltore")== null) {
            return "loginAgricoltore";
        }

        List<Prodotto> prodotti = prodottoService.getAllProdotti((Agricoltore) session.getAttribute("agricoltore"));

        for (Prodotto prodotto : prodotti) {
            if (prodotto.getImmagine() != null) {
                String immagineBase64 = Base64.getEncoder().encodeToString(prodotto.getImmagine());
                prodotto.setImmagineBase64(immagineBase64);
            }
        }
        model.addAttribute("prodotti", prodotti);
        return "pages/user/catalogo";
    }

    @GetMapping("/formModificaProdotto/{id}")
    public String formModificaProdotto(@PathVariable("id") int id,Model model, HttpSession session) {
        if(session.getAttribute("agricoltore")== null) {
            return "loginAgricoltore";
        }
        Prodotto prodotto = prodottoService.getProdottoById(id);
        model.addAttribute("prodottoMod", prodotto);
        return "pages/user/formModifica";
    }

    @PostMapping("/modProdotto")
    public String modificaProdottoForm(@ModelAttribute("prodottoMod") Prodotto prodotto,
                                       @RequestParam("immagineFile") MultipartFile immagineFile,
                                       Model model, HttpSession session) {
        try {
            if (!immagineFile.isEmpty()) {
                byte[] bytes = immagineFile.getBytes();
                prodotto.setImmagine(bytes);
            }

            prodotto.setAgricoltore((Agricoltore) session.getAttribute("agricoltore"));
            prodotto.setAcquistabile(true);

            prodottoService.saveAndFlushProdotto(prodotto);

            // Aggiorna la lista di prodotti nel model
            List<Prodotto> prodotti = prodottoService.getAllProdotti((Agricoltore) session.getAttribute("agricoltore"));
            for (Prodotto p : prodotti) {
                if (p.getImmagine() != null) {
                    String immagineBase64 = Base64.getEncoder().encodeToString(p.getImmagine());
                    p.setImmagineBase64(immagineBase64);
                }
            }
            model.addAttribute("prodotti", prodotti);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "pages/user/catalogo";
    }

    @GetMapping("/cancellaProdotto/{id}")
    public String cancellaProdotto(HttpSession session,Model model,@PathVariable("id") int id){
        Prodotto prodotto=prodottoService.getProdottoById(id);
        prodotto.setAcquistabile(false);
        prodottoService.saveAndFlushProdotto(prodotto);

        List<Prodotto> prodotti = prodottoService.getAllProdotti((Agricoltore) session.getAttribute("agricoltore"));
        for (Prodotto p : prodotti) {
            if (p.getImmagine() != null) {
                String immagineBase64 = Base64.getEncoder().encodeToString(p.getImmagine());
                p.setImmagineBase64(immagineBase64);
            }
        }
        model.addAttribute("prodotti", prodotti);

        return "pages/user/catalogo";
    }

  @GetMapping("/prodotto/{idProdotto}")
    String getProdotto(Model model, @PathVariable int idProdotto){
        Prodotto prodotto = prodottoService.getProdottoById(idProdotto);
        model.addAttribute("prodotto", prodotto);
        return "prodotto";
    }

    @GetMapping("/")
    public String getHome(){
        return "pages/user/home";
    }


    @PostMapping ("/ricerca")
    public String getProduct(@RequestParam String name, Model model) {
        if (name.trim().isEmpty() || name.length() > 50 || !name.matches("^[A-Za-zÀ-ù ‘-]{1,50}$")) {
            return "error.html";
        }

        List<Prodotto> risultatiRicerca = prodottoService.getResult(name);
        if (!risultatiRicerca.isEmpty()) {
            model.addAttribute("ricerca", risultatiRicerca);
        }
        return "pages/user/ricercaProdotto";
    }

}

