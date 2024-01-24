package com.greenbridge.services;

import com.greenbridge.entities.CarrelloCliente;
import com.greenbridge.entities.ListCart;
import com.greenbridge.entities.Ordine;
import com.greenbridge.entities.ProdottiOrdine;
import com.greenbridge.repositories.ProdottiOrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe di servizio per la gestione dei prodotti in un ordine.
 */
@Service
public class ProdottiOrdineService {

    @Autowired
    private ProdottiOrdineRepository prodottiOrdineRepository;




    /**
     * Restituisce tutti i prodotti in ordine presenti nel sistema.
     *
     * @return Una lista di tutti i prodotti in ordine.
     */
    public List<ProdottiOrdine> findAllProdottiOrdine() {
        return prodottiOrdineRepository.findAll();
    }

    /**
     * Restituisce tutti i prodotti in ordine associati a un determinato ordine.
     *
     * @param ordine L'ordine associato ai prodotti da cercare.
     * @return Una lista di prodotti in ordine associati all'ordine specificato.
     */
    public List<ProdottiOrdine> findAllProdottiOrdineByOrdine(Ordine ordine) {
        return prodottiOrdineRepository.findProdottiOrdineByOrdine(ordine);
    }

    /**
     * Salva un nuovo prodotto in ordine nel sistema.
     *
     * @param prodottiOrdine Il prodotto in ordine da salvare.
     * @return Il prodotto in ordine salvato.
     */
    public ProdottiOrdine saveProdottiOrdine(ProdottiOrdine prodottiOrdine) {
        return prodottiOrdineRepository.save(prodottiOrdine);
    }


    /**
     * Elimina un prodotto in ordine dal sistema usando l'ID.
     *
     * @param id L'ID del prodotto in ordine da eliminare.
     */
    public void deleteProdottiOrdineById(int id) {
        prodottiOrdineRepository.deleteById(id);
    }


    /**
     * Salva tutti i prodotti per un determinato ordine a partire da un carrello.
     *
     * @param listCart Il carrello contenente i prodotti da aggiungere all'ordine.
     * @param ordine   L'ordine a cui associare i prodotti.
     */
    public void saveAllProdottiPerOrdine(ListCart listCart, Ordine ordine) {
        for (CarrelloCliente carrelloCliente : listCart.getListCart()) {
            ProdottiOrdine prodottiOrdine = new ProdottiOrdine(carrelloCliente.
                    getProdotto(), ordine, carrelloCliente.getKgRichiesti(),
                    carrelloCliente.getProdotto().getPrezzoKg());
            saveProdottiOrdine(prodottiOrdine);
        }
    }

    /**
     * Restituisce tutti i prodotti in ordine associati a un determinato ordine usando l'ID dell'ordine.
     *
     * @param ordine L'ordine associato ai prodotti da cercare.
     * @return Una lista di prodotti in ordine associati all'ordine specificato.
     */
    public List<ProdottiOrdine> findAllProdottiOrdineByOrdineId(Ordine ordine) {
        return prodottiOrdineRepository.findProdottiOrdineByOrdine(ordine);
    }
}
