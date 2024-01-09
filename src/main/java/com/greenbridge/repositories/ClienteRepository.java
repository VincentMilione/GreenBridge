package com.greenbridge.repositories;

import com.greenbridge.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    public boolean existsByEmail(String email);

    Cliente findByEmail(String email);




}
