package com.greenbridge.services;

import com.greenbridge.entities.Ordine;
import com.greenbridge.repositories.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdineService {

    private final OrdineRepository ordineRepository;

    @Autowired
    public OrdineService(OrdineRepository ordineRepository) {
        this.ordineRepository = ordineRepository;
    }

    public Ordine salvaOrdine(Ordine ordine) {
        return ordineRepository.save(ordine);
    }

    public Ordine getOrdineById(Integer id) {
        return ordineRepository.findOrdineById(id);
    }


}
