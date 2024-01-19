package com.greenbridge.services;



import com.greenbridge.entities.Portafoglio;
import com.greenbridge.repositories.PortafoglioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PortafoglioService {
    @Autowired
    private PortafoglioRepository portafoglioRepository;


    public Portafoglio getPortafoglioById(int id) {
       return portafoglioRepository.findById(id).get();
    }

    public void salvaPortafoglio(Portafoglio portafoglio) {
         portafoglioRepository.save(portafoglio);
    }




}