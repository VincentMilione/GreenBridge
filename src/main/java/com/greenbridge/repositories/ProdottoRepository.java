package com.greenbridge.repositories;

import com.greenbridge.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer>{
    List<Prodotto> findByNomeContainingIgnoreCase(String nome);
    Prodotto getProdottoByIdProdotto(int id);
}
