package com.greenbridge.repositories;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.entities.Certificato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * Repository interface for performing CRUD operations on Certificato entities.
 */
public interface CertificatoRepository extends JpaRepository<Certificato, Integer> {
    /**
     * Retrieves a list of Certificato entities associated with a specific Agricoltore.
     *
     * @param agricoltore Agricoltore entity for which to retrieve Certificato entities
     * @return List of Certificato entities associated with the provided Agricoltore
     */
    List<Certificato> findByAgricoltore(Agricoltore agricoltore);
}
