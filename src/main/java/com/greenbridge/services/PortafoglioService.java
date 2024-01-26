package com.greenbridge.services;
import com.greenbridge.entities.Portafoglio;
import com.greenbridge.repositories.PortafoglioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Classe di servizio per la gestione del portafoglio.
 * @author Salvatore Mattiello
 */
@Service
public class PortafoglioService {
    /** repository del portafoglio.*/
    @Autowired
    private PortafoglioRepository portafoglioRepository;


    /**
     * Ottiene un oggetto {@link Portafoglio} dal repository usando l'ID.
     *
     * @param id L'ID del portafoglio da ottenere.
     * @return L'oggetto {@link Portafoglio} corrispondente
     * all'ID specificato.
     */
    public Portafoglio getPortafoglioById(int id) {
       return portafoglioRepository.findById(id).get();
    }

    /**
     * Salva un nuovo portafoglio nel repository.
     *
     * @param portafoglio Il portafoglio da salvare.
     */
    public void salvaPortafoglio(Portafoglio portafoglio) {
         portafoglioRepository.save(portafoglio);
    }




}