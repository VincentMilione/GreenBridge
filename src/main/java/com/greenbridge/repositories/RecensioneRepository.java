package com.greenbridge.repositories;

import com.greenbridge.entities.RecensioneProdotti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecensioneRepository extends
        JpaRepository<RecensioneProdotti, Integer> {

    List<RecensioneProdotti> findAllByIdProdotto(int id);
}
