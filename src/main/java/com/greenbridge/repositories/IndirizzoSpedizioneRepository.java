package com.greenbridge.repositories;

import com.greenbridge.entities.IndirizzoSpedizione;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository per l'entità IndirizzoSpedizione.
 * @Author Salvatore Mattiello
 */
public interface IndirizzoSpedizioneRepository
        extends JpaRepository<IndirizzoSpedizione, Integer> {

}
