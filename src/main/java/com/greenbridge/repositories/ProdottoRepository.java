package com.greenbridge.repositories;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interfaccia per l'accesso ai dati dei prodotti nel database.
 * Autore: Mauro
 */
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {

    /**
     * Trova tutti i prodotti di un agricoltore che sono acquistabili.
     *
     * @param agricoltore l'agricoltore di cui trovare i prodotti
     * @return una lista di prodotti acquistabili
     */
    List<Prodotto> findAllByAgricoltoreAndAcquistabileTrue(
            Agricoltore agricoltore);

    /**
     * Ottiene un prodotto dal database dato l'ID del prodotto.
     *
     * @param id l'ID del prodotto da ottenere
     * @return il prodotto corrispondente all'ID specificato
     */
    Prodotto getProdottoByIdProdotto(int id);

    /**
     * Trova tutti i prodotti nel database
     * il cui nome contiene la stringa specificata,
     * ignorando maiuscole e minuscole.
     *
     * @param nome la stringa da cercare nel nome dei prodotti
     * @return una lista di prodotti con il nome che
     * contiene la stringa specificata
     */
    List<Prodotto> findByNomeContainingIgnoreCase(String nome);
}
