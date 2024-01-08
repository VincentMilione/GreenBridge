package com.greenbridge.services;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.repositories.AgricoltoreRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgricoltoreServiceImpl implements AgricoltoreService {

    private final AgricoltoreRepository agricoltoreRepository;
    public AgricoltoreServiceImpl(AgricoltoreRepository agricoltoreRepository) {
        this.agricoltoreRepository = agricoltoreRepository;
    }

    @Override
    public List<Agricoltore> getAgricoltori() {
        return agricoltoreRepository.findAll();
    }

    @Override
    public Agricoltore saveAgricoltore(Agricoltore agricoltore) {
        if (agricoltoreRepository.existsById(agricoltore.getId()))
            throw new RuntimeException("utente gia esiste");
        return agricoltoreRepository.save(agricoltore);
    }

    @Override
    public void modificaAgricoltore(Agricoltore agricoltore) {
        if (!agricoltoreRepository.existsById(agricoltore.getId()))
            throw new RuntimeException("utente non esiste");
        agricoltoreRepository.save(agricoltore);
    }

    @Override
    public Agricoltore getSingleAgricoltore(int id) {
        Optional<Agricoltore> agricoltoreOptional = agricoltoreRepository.findById(id);
        // Se l'agricoltore non è presente, restituisci null
        return agricoltoreOptional.orElse(null);
    }
    @Override
    public Agricoltore getAgricoltoreByEmail(String email) {
        return this.agricoltoreRepository.findByEmail(email);
    }


}









