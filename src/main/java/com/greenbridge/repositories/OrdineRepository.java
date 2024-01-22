package com.greenbridge.repositories;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Ordine;
import com.greenbridge.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdineRepository extends JpaRepository<Ordine, Integer> {
    // Puoi aggiungere eventuali metodi personalizzati qui, se necessario
    List<Ordine> findByAgricoltore(Agricoltore agricoltore);
}
