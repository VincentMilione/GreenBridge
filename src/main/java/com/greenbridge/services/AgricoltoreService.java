package com.greenbridge.services;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.repositories.AgricoltoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgricoltoreService {
    @Autowired
    AgricoltoreRepository agricoltoreRepository;
    public void save(Agricoltore agricoltore) {
        if(agricoltoreRepository.existsById(agricoltore.getId()))
            throw new RuntimeException("utente gia esiste");
        agricoltoreRepository.save(agricoltore);
    }

    public void modifyUser(Agricoltore agricoltore) {
        if (!agricoltoreRepository.existsById(agricoltore.getId()))
            throw new RuntimeException("utente non esiste");
        agricoltoreRepository.save(agricoltore);
    }




//    public void deleteAgricoltoreById(long id) {
//       this.agricoltoreRepository.deleteById(id);
//    }
}
