package com.greenbridge.services;

import com.greenbridge.entities.Cliente;
import com.greenbridge.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/** Questa classe fornisce dei servizi
 * utili nella gestione dell'utente cliente. */
@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /** Questo metodo consente di salvare
     *  un nuovo utente cliente nel database. */
    public void saveCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    /** Questo metodo consente di ottenere
     *  tutti gli utenti cliente nel database. */
    public List<Cliente> getAllClienti() {
        return clienteRepository.findAll();
    }

    /** Questo metodo consente di ottenere
     *  un utente data un Email in input. */
    public Cliente getClienteByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }
    /** Questo metodo consente di verificare
     * se un utente esiste nel database data una Email in input. */
    public boolean clienteExistsByEmail(Cliente cliente) {
        return clienteRepository.existsByEmail(cliente.getEmail());
    }
}
