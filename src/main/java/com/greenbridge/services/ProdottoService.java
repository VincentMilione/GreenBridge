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

    public List<Prodotto> getResult(String Nome) {
            return prodottoRepository.findByNomeContainingIgnoreCase(Nome);
    }

    public Prodotto getProdottoById(int idProdotto){
        return prodottoRepository.getProdottoByIdProdotto(idProdotto);
    }

}
