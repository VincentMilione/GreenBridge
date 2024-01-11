package com.greenbridge.services;

import com.greenbridge.entities.ProdottiOrdine;
import com.greenbridge.repositories.ProdottiOrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottiOrdineService {

    @Autowired
    private ProdottiOrdineRepository prodottiOrdineRepository;




    public List<ProdottiOrdine> findAllProdottiOrdine() {
        return prodottiOrdineRepository.findAll();
    }

    public ProdottiOrdine saveProdottiOrdine(ProdottiOrdine prodottiOrdine) {
        return prodottiOrdineRepository.save(prodottiOrdine);
    }

    public void deleteProdottiOrdineById(int id) {
        prodottiOrdineRepository.deleteById(id);
    }
}
