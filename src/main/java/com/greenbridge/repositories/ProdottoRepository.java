package com.greenbridge.repositories;

import com.greenbridge.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdottoRepository extends JpaRepository<Prodotto,String>
{
    List<Prodotto> findAllByIdAgricoltoreAndAcquistabileTrue(int idAgricoltore);
    Prodotto getProdottoByIdProdotto(int id);
}
