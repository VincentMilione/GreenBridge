package com.greenbridge.controllers;

import com.greenbridge.entities.Prodotto;
import com.greenbridge.services.ProdottoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/formInserimento")
    public String getProdotto(Model model, HttpSession session) {
        int idAgricoltore = 1;
        session.setAttribute("idAgricoltore", idAgricoltore);
        model.addAttribute("prodotto", new Prodotto());
        return "pages/user/formInserimento";
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

            prodotto.setIdAgricoltore((Integer)
                    session.getAttribute("idAgricoltore"));

            prodotto.setAcquistabile(true);

            prodottoService.saveProdotto(prodotto);

            // Aggiorna la lista di prodotti nel model
            List<Prodotto> prodotti = prodottoService.getAllProdotti((Integer)
                    session.getAttribute("idAgricoltore"));
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

    @GetMapping("/catalogo")
    public String getCatalogo(Model model, HttpSession session) {
        int idAgricoltore = 1;
        session.setAttribute("idAgricoltore", idAgricoltore);
        List<Prodotto> prodotti = prodottoService.getAllProdotti((Integer) session.getAttribute("idAgricoltore"));

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
    public String formModificaProdotto(@PathVariable("id") int id,Model model) {
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

            prodotto.setIdAgricoltore((Integer) session.getAttribute("idAgricoltore"));
            prodotto.setAcquistabile(true);

            prodottoService.saveAndFlushProdotto(prodotto);

            // Aggiorna la lista di prodotti nel model
            List<Prodotto> prodotti = prodottoService.getAllProdotti((Integer)
                    session.getAttribute("idAgricoltore"));
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

        List<Prodotto> prodotti = prodottoService.getAllProdotti((Integer)
                session.getAttribute("idAgricoltore"));
        for (Prodotto p : prodotti) {
            if (p.getImmagine() != null) {
                String immagineBase64 = Base64.getEncoder().encodeToString(p.getImmagine());
                p.setImmagineBase64(immagineBase64);
            }
        }
        model.addAttribute("prodotti", prodotti);

        return "pages/user/catalogo";
    }








}



