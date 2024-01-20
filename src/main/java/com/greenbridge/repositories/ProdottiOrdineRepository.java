package com.greenbridge.repositories;

import com.greenbridge.entities.Ordine;
import com.greenbridge.entities.ProdottiOrdine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdottiOrdineRepository extends JpaRepository<ProdottiOrdine, Integer> {
    // Puoi aggiungere eventuali query personalizzate qui
    public List<ProdottiOrdine> findProdottiOrdineByOrdine(Ordine ordine);
}
