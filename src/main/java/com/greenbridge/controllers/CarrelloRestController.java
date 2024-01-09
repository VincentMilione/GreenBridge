package com.greenbridge.controllers;

import com.greenbridge.entities.List_Cart;
import com.greenbridge.entities.Prodotto;
import com.greenbridge.services.CarrelloClienteService;
import com.greenbridge.services.ProdottoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/carrello")
public class CarrelloRestController {
    @Autowired
    private ProdottoService prodottoService;
    @Autowired
    private CarrelloClienteService carrelloClienteService;

    //aggiungere un prodotto al carrello
    @PostMapping("/aggiungiCart")
    int aggiungiCart(@RequestParam Integer prodotto_id,@RequestParam int quantita, HttpSession session) {
        Prodotto prodotto = prodottoService.getProdottoById(prodotto_id);
        List_Cart list_cart = (List_Cart) session.getAttribute("list_cart");
        if(list_cart.isPresent(prodotto_id))
            return 0;

        list_cart.addCart(prodotto,quantita);
        return 1;

    }
//AGGIUNGERE DA  QUI
        @PostMapping("/edit_prodotto")
    float[] edit_prodotto(@RequestParam Integer prodotto_id,@RequestParam String edit, HttpSession session){
            List_Cart list_cart = (List_Cart) session.getAttribute("list_cart");
            if(edit.equals("delete")){
                return list_cart.delete(prodotto_id);
            }else{
                Prodotto prodotto = prodottoService.getProdottoById(prodotto_id);
                carrelloClienteService.deleteByProdotto(prodotto);
                return list_cart.edit_prodotto(prodotto_id, edit);

            }

        }







}
