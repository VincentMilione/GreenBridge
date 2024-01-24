package com.greenbridge.repositories;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Certificato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificatoRepository extends JpaRepository<Certificato, Integer> {
   // List<Certificato> getAgricoltoreByCertificatoNome();

    List<Certificato> findByAgricoltore(Agricoltore agricoltore);
}
