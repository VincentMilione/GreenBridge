package com.greenbridge.services;

import com.greenbridge.entities.RecensioneProdotti;

import java.util.List;

public interface RecensioneService {

    void saveRecensioneProdotto(RecensioneProdotti recensione);
    List<RecensioneProdotti> getRecensioniByIdProdotto(int idProdotto);
}
