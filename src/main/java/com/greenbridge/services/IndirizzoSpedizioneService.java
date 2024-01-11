package com.greenbridge.services;

import com.greenbridge.entities.IndirizzoSpedizione;
import com.greenbridge.repositories.IndirizzoSpedizioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class IndirizzoSpedizioneService {


    @Autowired
    private  IndirizzoSpedizioneRepository indirizzoSpedizioneRepository;

    // Metodo per salvare un indirizzo di spedizione nel database
    @Transactional
    public IndirizzoSpedizione saveIndirizzoSpedizione(IndirizzoSpedizione indirizzoSpedizione) {
        return indirizzoSpedizioneRepository.save(indirizzoSpedizione);
    }

    // Puoi aggiungere altri metodi del service se necessario
}
