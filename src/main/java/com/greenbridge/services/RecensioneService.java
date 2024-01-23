package com.greenbridge.services;

import com.greenbridge.entities.RecensioneProdotti;

import java.util.List;

/**
 * Questa classe dispone una serie di metodi
 * che permettono di interagire con
 * la classe recensione prodotto
 * @author Michele Martino
 */
public interface RecensioneService {
    /**
     * Questo metodo consetne di salvare una recensione prodotto nel database
     * @param recensione recensione prodotto da salvare
     */
    void saveRecensioneProdotto(RecensioneProdotti recensione);

    /**
     * Questo metodo recupera tutte le recensione
     * di un prodotto
     * @param idProdotto identificativo del prodotto di cui
     *                   ottenere le recensioni
     * @return Lista con le recensione del prodotto in input
     */
    List<RecensioneProdotti> getRecensioniByIdProdotto(int idProdotto);
}
