package com.greenbridge.repositories;

import com.greenbridge.entities.Agricoltore;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository interface for performing CRUD
 * operations on Agricoltore entities.
 */
public interface AgricoltoreRepository extends
          JpaRepository<Agricoltore, Integer> {
    /**
     * Retrieves an Agricoltore entity by email.
     *
     * @param email Email of the Agricoltore to retrieve
     * @return Agricoltore entity corresponding to the provided email
     */
    Agricoltore findByEmail(String email);


}
