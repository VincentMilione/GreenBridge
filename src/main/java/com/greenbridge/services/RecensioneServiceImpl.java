package com.greenbridge.services;

import com.greenbridge.entities.RecensioneProdotti;
import com.greenbridge.repositories.RecensioneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Questa classe dispone l'implementazione di una serie di metodi
 * che permettono di interagire con
 * la classe recensione prodotto.
 * @author Michele Martino
 */
@Service
public class RecensioneServiceImpl implements RecensioneService {
    /**
     * Questo attributo consente l'interazione con le recensioni nel database.
     */
    private final RecensioneRepository recensioneRepository;

    /**
     * Costruttore che inizializza l'attributo che consente
     * l'interazione con il database.
     * @param recensioneRepository repository da utilizzare
     *                            per l'interazione con il database
     */
    public RecensioneServiceImpl(RecensioneRepository recensioneRepository) {
        this.recensioneRepository = recensioneRepository;
    }
    /**
     * Questo metodo consetne di salvare una recensione prodotto nel database.
     * @param recensione recensione prodotto da salvare.
     */
    @Override
    public void saveRecensioneProdotto(RecensioneProdotti recensione) {
        recensioneRepository.save(recensione);
    }
    /**
     * Questo metodo recupera tutte le recensione
     * di un prodotto.
     * @param idProdotto identificativo del prodotto di cui
     *                   ottenere le recensioni
     * @return Lista con le recensione del prodotto in input
     */
    @Override
    public List<RecensioneProdotti> getRecensioniByIdProdotto(int idProdotto) {
        return recensioneRepository.findAllByIdProdotto(idProdotto);
    }


}
