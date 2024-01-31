package com.greenbridge.services;

import com.greenbridge.entities.IndirizzoSpedizione;
import com.greenbridge.repositories.IndirizzoSpedizioneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Indirizzo di spedizione.
 * @author Salvatore Mattiello
 */
@Service
public class IndirizzoSpedizioneService {

    /**
     * Repository Indirizzo di spedizione.
     */

    @Autowired
    private  IndirizzoSpedizioneRepository indirizzoSpedizioneRepository;

    /** Metodo per salvare un indirizzo di spedizione nel database.
     *
     * @param indirizzoSpedizione da salvare.
     * @return indirizzoSpedizione salvato.
     */
    @Transactional
    public IndirizzoSpedizione saveIndirizzoSpedizione(IndirizzoSpedizione
                                   indirizzoSpedizione) {
        return indirizzoSpedizioneRepository.save(indirizzoSpedizione);
    }

}
