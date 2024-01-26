package com.greenbridge.services;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Prodotto;
import com.greenbridge.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servizio per la gestione dei prodotti.
 * Autore: Mauro
 */
@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    /**
     * Salva un prodotto nel database.
     *
     * @param prodotto il prodotto da salvare
     */
    public void saveProdotto(Prodotto prodotto) {
        prodottoRepository.save(prodotto);
    }

    /**
     * Ottiene tutti i prodotti acquistabili di un agricoltore.
     *
     * @param agricoltore l'agricoltore di cui trovare i prodotti
     * @return una lista di prodotti acquistabili
     */
    public List<Prodotto> getAllProdotti(Agricoltore agricoltore) {
        return prodottoRepository.findAllByAgricoltoreAndAcquistabileTrue(agricoltore);
    }

    /**
     * Ottiene un prodotto dato l'ID del prodotto.
     *
     * @param idProdotto l'ID del prodotto da ottenere
     * @return il prodotto corrispondente all'ID specificato
     */
    public Prodotto getProdottoById(int idProdotto) {
        return prodottoRepository.getProdottoByIdProdotto(idProdotto);
    }

    /**
     * Salva e effettua un flush immediato nel database.
     *
     * @param prodotto il prodotto da salvare e fare flush
     */
    public void saveAndFlushProdotto(Prodotto prodotto) {
        prodottoRepository.saveAndFlush(prodotto);
    }

    /**
     * Ottiene una lista di prodotti il cui nome contiene la stringa specificata,
     * ignorando maiuscole e minuscole.
     *
     * @param nome la stringa da cercare nel nome dei prodotti
     * @return una lista di prodotti con il nome che contiene la stringa specificata
     */
    public List<Prodotto> getResult(String nome) {
        return prodottoRepository.findByNomeContainingIgnoreCase(nome);
    }
}
