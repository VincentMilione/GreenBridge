package com.greenbridge.controllers;

import com.greenbridge.entities.ListCart;
import com.greenbridge.entities.Prodotto;
import com.greenbridge.services.CarrelloClienteService;
import com.greenbridge.services.ProdottoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



/**
 * Controller REST per gestire le operazioni del carrello degli acquisti.
 * @author salvatore mattiello
 */
@RestController
@RequestMapping("/carrello")
public class CarrelloRestController {
    /**
     * Servizio per la gestione delle
     * operazioni legate ai prodotti.
     */
    @Autowired
    private ProdottoService prodottoService;
    /**
     * Servizio per la gestione delle
     * operazioni legate al carrello.
     */
    @Autowired
    private CarrelloClienteService carrelloClienteService;

    /**
     * Metodo per aggiungere un prodotto al carrello.
     *
     * @param idProdotto Identificativo del prodotto da aggiungere.
     * @param quantita   Quantità del prodotto da aggiungere.
     * @param session    Sessione HTTP per mantenere lo stato del carrello.
     * @return 0 se il prodotto è già presente nel carrello, 1 altrimenti.
     */
    @PostMapping("/aggiungiCart")
    int aggiungiCart(@RequestParam Integer idProdotto,
                     @RequestParam int quantita, HttpSession session) {
        Prodotto prodotto = prodottoService.getProdottoById(idProdotto);
        ListCart listCart = (ListCart) session.getAttribute("list_cart");
        if (listCart.isPresent(idProdotto)) {
            return 0;
        }

        listCart.addCart(prodotto, quantita);
        return 1;

    }


    /**
     * Metodo per modificare un prodotto
     * nel carrello (elimina o modifica quantità).
     *
     * @param idProdotto Identificativo del prodotto
     *                   da modificare.
     * @param edit       Tipo di modifica
     *                   ("delete" per eliminare, altrimenti modifica la quantità).
     * @param session    Sessione HTTP per mantenere lo stato del carrello.
     * @return Array di float contenente informazioni
     * aggiornate sul carrello (nuova quantità e nuovo prezzo totale).
     */
    @PostMapping("/edit_prodotto")
    float[] editProdotto(@RequestParam Integer idProdotto,
                         @RequestParam String edit, HttpSession session) {
        ListCart listCart = (ListCart) session.getAttribute("list_cart");
        if (edit.equals("delete")) {
            return listCart.delete(idProdotto);
        } else {
            Prodotto prodotto =
                    prodottoService.getProdottoById(idProdotto);
            carrelloClienteService.deleteByProdotto(prodotto);
            return listCart.editProdotto(idProdotto, edit);

        }

    }







}