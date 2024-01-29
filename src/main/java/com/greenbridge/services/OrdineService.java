package com.greenbridge.services;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Cliente;
import com.greenbridge.entities.Ordine;
import com.greenbridge.repositories.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



/**
 * Classe di servizio per la gestione degli ordini.
 */
@Service
public class OrdineService {
/**
 * repository di ordine.
 * */
    private final OrdineRepository ordineRepository;
/**
 Assegnamento valore alla repository
 * */
    @Autowired
    public OrdineService(OrdineRepository ordineRepository) {
        this.ordineRepository = ordineRepository;
    }

    /**
     * Salva un nuovo ordine nel repository.
     *
     * @param ordine L'ordine da salvare.
     * @return L'ordine salvato.
     */
    public Ordine salvaOrdine(Ordine ordine) {
        return ordineRepository.save(ordine);
    }

    /**
     * Ottiene tutti gli ordini associati a un agricoltore.
     *
     * @param agricoltore L'agricoltore associato agli ordini da cercare.
     * @return Una lista di ordini associati all'agricoltore specificato.
     */
    public List<Ordine> getOrdiniByAgricoltore(Agricoltore agricoltore) {
        List<Ordine> ordini = ordineRepository.findByAgricoltore(agricoltore);
        return ordini;
    }

    /**
     * Ottiene un ordine dal repository usando l'ID.
     *
     * @param id L'ID dell'ordine da ottenere.
     * @return L'ordine corrispondente all'ID specificato, se presente.
     */
    public Ordine getOrdineById(Integer id) {
        return ordineRepository.findOrdineById(id);
    }


    /**
     * Ottiene tutti gli ordini associati a un agricoltore.
     *
     * @param cliente Il cliente associato agli ordini da cercare.
     * @return Una lista di ordini associati all'agricoltore specificato.
     */
    public List<Ordine> getOrdiniByCliente(Cliente cliente) {
        List<Ordine> ordini = ordineRepository.findByCliente(cliente);
        return ordini;
    }

}
