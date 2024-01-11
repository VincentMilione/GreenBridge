package com.greenbridge.repositories;

import com.greenbridge.entities.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineRepository extends JpaRepository<Ordine, Integer> {
    // Puoi aggiungere eventuali metodi personalizzati qui, se necessario
}
