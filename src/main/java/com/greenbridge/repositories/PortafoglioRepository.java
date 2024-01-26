package com.greenbridge.repositories;

import com.greenbridge.entities.Portafoglio;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository per l'entità Portafoglio.
 * @author Salvatore Mattiello
 */
public interface PortafoglioRepository
        extends JpaRepository<Portafoglio, Integer> {
}
