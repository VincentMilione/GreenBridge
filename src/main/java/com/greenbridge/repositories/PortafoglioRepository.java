package com.greenbridge.repositories;

import com.greenbridge.entities.Portafoglio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortafoglioRepository
        extends JpaRepository<Portafoglio, Integer> {
    // Puoi aggiungere eventuali metodi personalizzati qui, se necessario
}
