package com.greenbridge.repositories;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {
    List<Prodotto> findAllByAgricoltoreAndAcquistabileTrue(Agricoltore agricoltore);
    Prodotto getProdottoByIdProdotto(int id);
}
