package com.greenbridge.services;

import com.greenbridge.entities.Cliente;
import com.greenbridge.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/** Questa classe fornisce
 * l'implementazione dei metodi
 * utili nella gestione dell'utente cliente.
 * @author Michele Martino
 * */
@Service
public class ClienteServiceImpl implements ClienteService {
    /**
     * Attributo che permette di reperire i dati
     * dal database in merito al cliente
     */
    private final ClienteRepository clienteRepository;

    /**
     * Costruttore che inizializza clienteRepository
     * @param clienteRepository cura l'interazione con il database
     */
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Il metodo consente di salvare un cliente nel database
     * @param cliente cliente da salvare nel database
     */
    public void saveCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    /**
     * Il metodo permette di ottenere tutti i clienti
     * @return Lista con tutti i clienti
     */
    public List<Cliente> getAllClienti() {
        return clienteRepository.findAll();
    }

    /**
     * Il metodo consente di cercare un cliente per Email
     * @param email email con cui cercare l'utente
     * @return Cliente corrispondente alla Email (se esiste)
     */
    public Cliente getClienteByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }
    /**
     * Il metodo controlla l'esistenza di un cliente
     * @param cliente Cliente di cui verificare l'esistenza
     * @return esito della ricerca
     */
    public boolean clienteExistsByEmail(Cliente cliente) {
        return clienteRepository.existsByEmail(cliente.getEmail());
    }
    /**
     * Il metodo cerca un cliente per id
     * @param id id del cliente da cercare
     * @return il cliente che corrisponde all'id
     */
    public Cliente getClienteById(int id) {
        return clienteRepository.findById(id).get();
    }
}