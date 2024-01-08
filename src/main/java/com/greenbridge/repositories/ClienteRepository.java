package com.greenbridge.repositories;

import com.greenbridge.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/** Questa classe contente l'accesso al database ai dati relativi al cliente. */
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    /** Questo metodo consente di sapere se
     *  esiste o meno un cliente associato ad una Email data in input. */
    boolean existsByEmail(String email);

    /** Questo metodo permette di cercare un utente cliente per Email */
    Cliente findByEmail(String email);
}
