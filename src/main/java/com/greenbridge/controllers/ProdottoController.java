package com.greenbridge.controllers;

import com.greenbridge.entities.Prodotto;
import com.greenbridge.services.ProdottoService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintStream;
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
            //
            if (!immagineFile.isEmpty()) {
                byte[] Bytes = immagineFile.getBytes();
                prodotto.setImmagine(Bytes);
                prodotto.setIdAgricoltore((Integer) session.getAttribute("idAgricoltore"));
                prodotto.setAcquistabile(true);
                prodottoService.saveProdotto(prodotto);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        return "pages/user/success";
    }










}



