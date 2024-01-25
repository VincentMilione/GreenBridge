package com.greenbridge.repositories;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Cliente;
import com.greenbridge.entities.Ordine;
import com.greenbridge.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interfaccia del repository per l'entità Ordine.
 * Estende JpaRepository fornendo metodi predefiniti per l'accesso ai dati dell'entità Ordine.
 *
 * @author Giuseppe Di Sarno
 */
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {
    /**
     * Trova tutti gli ordini associati a un determinato agricoltore.
     *
     * @param agricoltore Agricoltore di cui cercare gli ordini.
     * @return Lista di ordini associati all'agricoltore specificato.
     */
    List<Ordine> findByAgricoltore(Agricoltore agricoltore);
    /**
     * Trova un ordine dato il suo identificativo.
     *
     * @param id Identificativo dell'ordine da cercare.
     * @return L'ordine corrispondente all'identificativo specificato.
     */
    public Ordine findOrdineById(int id);

    /**
     * Trova tutti gli ordini associati a un determinato cliente.
     *
     * @param cliente cliente di cui cercare gli ordini.
     * @return Lista di ordini associati all'agricoltore specificato.
     */
    List<Ordine> findByCliente(Cliente cliente);
}
