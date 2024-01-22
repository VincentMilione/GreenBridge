package com.greenbridge.services;

import com.greenbridge.entities.RecensioneProdotti;
import com.greenbridge.repositories.RecensioneRepository;
import org.springframework.stereotype.Service;

@Service
public class RecensioneServiceImpl implements RecensioneService {

    private final RecensioneRepository recensioneRepository;

    public RecensioneServiceImpl(RecensioneRepository recensioneRepository) {
        this.recensioneRepository = recensioneRepository;
    }

    @Override
    public void saveRecensioneProdotto(RecensioneProdotti recensione) {
        recensioneRepository.save(recensione);
    }
}
