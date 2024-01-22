package com.greenbridge.services;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Ordine;
import com.greenbridge.repositories.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Ordine> getOrdiniByAgricoltore(Agricoltore agricoltore){
        List<Ordine> ordini = ordineRepository.findByAgricoltore(agricoltore);
        return ordini;
    }

    public Ordine getOrdineById(int id){
        return ordineRepository.findById(id).get();
    }


}
