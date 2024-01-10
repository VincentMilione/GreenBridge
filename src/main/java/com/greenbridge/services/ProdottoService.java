package com.greenbridge.services;

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

    public void saveProdotto(Prodotto prodotto){
        prodottoRepository.save(prodotto);
    }

    public List<Prodotto> getAllProdotti(int idAgricoltore) {
        return prodottoRepository.findAllByIdAgricoltoreAndAcquistabileTrue(idAgricoltore);
    }
    public Prodotto getProdottoById(int idProdotto){
        return prodottoRepository.getProdottoByIdProdotto(idProdotto);
    }

    public void saveAndFlushProdotto(Prodotto prodotto) {
        prodottoRepository.saveAndFlush(prodotto);
    }

    public List<Prodotto> getResult(String Nome) {return prodottoRepository.findByNomeContainingIgnoreCase(Nome);
    }


}