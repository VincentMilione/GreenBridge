package com.greenbridge.services;

import com.greenbridge.entities.Cliente;

import java.util.List;

/** Questa classe fornisce dei servizi
 * utili nella gestione dell'utente cliente. */
public interface ClienteService {
    /** Questo metodo consente di salvare
     * un nuovo utente cliente nel database. */
    void saveCliente(Cliente cliente);
    /** Questo metodo consente di ottenere
     * tutti gli utenti cliente nel database. */
    List<Cliente> getAllClienti();
    /** Questo metodo consente di ottenere un utente data un Email in input. */
    Cliente getClienteByEmail(String email);
    /** Questo metodo consente di verificare
     * se un utente esiste nel database data una Email in input. */
    boolean clienteExistsByEmail(Cliente cliente);
}
