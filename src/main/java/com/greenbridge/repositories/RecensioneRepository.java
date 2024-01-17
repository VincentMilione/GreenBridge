package com.greenbridge.repositories;

import com.greenbridge.entities.RecensioneProdotti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecensioneRepository extends JpaRepository<RecensioneProdotti, Integer> {

}
