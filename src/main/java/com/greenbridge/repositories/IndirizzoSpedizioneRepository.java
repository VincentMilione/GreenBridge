package com.greenbridge.repositories;

import com.greenbridge.entities.IndirizzoSpedizione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndirizzoSpedizioneRepository extends JpaRepository<IndirizzoSpedizione, Integer> {
    // Puoi aggiungere eventuali metodi personalizzati qui, se necessario
}
