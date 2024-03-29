package com.greenbridge.repositories;

import com.greenbridge.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/** Questa classe contente l'accesso al database ai dati relativi al cliente.
 * @author Michele Martino
 * */
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    /** Questo metodo consente di sapere se
     *  esiste o meno un cliente associato ad una Email data in input.
     * @param email email dell'utente di cui verificare l'esistenza
     * @return booleano che sancisce esito della ricerca
     * */
    boolean existsByEmail(String email);

    /** Questo metodo permette di cercare un utente cliente per Email
     * @param email email dell'utente da cercare
     * @return Cliente cercato per email (se esiste)
     * */
    Cliente findByEmail(String email);




}
