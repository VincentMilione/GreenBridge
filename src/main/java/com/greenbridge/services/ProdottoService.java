package com.greenbridge.services;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Prodotto;
import com.greenbridge.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoService {
    @Autowired
    private ProdottoRepository prodottoRepository;



    public List<Prodotto> getAllProdotti(Agricoltore agricoltore) {
        return prodottoRepository.findAllByAgricoltoreAndAcquistabileTrue(agricoltore);
    }
    public Prodotto getProdottoById(int idProdotto){
        return prodottoRepository.getProdottoByIdProdotto(idProdotto);
    }

    public void saveAndFlushProdotto(Prodotto prodotto) {
        prodottoRepository.saveAndFlush(prodotto);
    }

}
