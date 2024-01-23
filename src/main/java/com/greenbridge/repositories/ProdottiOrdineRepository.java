package com.greenbridge.repositories;

import com.greenbridge.entities.Ordine;
import com.greenbridge.entities.ProdottiOrdine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.List;


/**
 * Interfaccia del repository per l'entità ProdottiOrdine.
 * Estende JpaRepository fornendo metodi predefiniti per l'accesso ai dati dell'entità ProdottiOrdine.
 *
 * @author Giuseppe Di Sarno
 */
public interface ProdottiOrdineRepository extends JpaRepository<ProdottiOrdine, Integer> {

    /**
     * Trova tutti i prodotti presenti in un determinato ordine.
     *
     * @param ordine Ordine di cui cercare i prodotti associati.
     * @return Lista di prodotti associati all'ordine specificato.
     */
    List<ProdottiOrdine> findProdottiOrdineByOrdine(Ordine ordine);
}