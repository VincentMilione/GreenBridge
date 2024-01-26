package com.greenbridge.repositories;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Certificato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificatoRepository extends JpaRepository<Certificato, Integer> {

}
