package com.greenbridge.services;

import com.greenbridge.entities.RecensioneProdotti;
import com.greenbridge.repositories.RecensioneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<RecensioneProdotti> getRecensioniByIdProdotto(int idProdotto) {
        return recensioneRepository.findAllByIdProdotto(idProdotto);
    }


}
