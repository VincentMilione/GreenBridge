package com.greenbridge.services;

import com.greenbridge.entities.Cliente;
import java.util.List;

/**
 * Questa classe dispone una serie di metodi
 * che permettono di interagire con la classe cliente
 * @author Michele Martino
 */
public interface ClienteService {
    /**
     * Il metodo consente di salvare un cliente nel database
     * @param cliente cliente da salvare nel database
     */
    void saveCliente(Cliente cliente);
    /**
     * Il metodo permette di ottenere tutti i clienti
     * @return Lista con tutti i clienti
     */
    List<Cliente> getAllClienti();
    /**
     * Il metodo consente di cercare un cliente per Email
     * @param email email con cui cercare l'utente
     * @return Cliente corrispondente alla Email (se esiste)
     */
    Cliente getClienteByEmail(String email);

    /**
     * Il metodo controlla l'esistenza di un cliente
     * @param cliente Cliente di cui verificare l'esistenza
     * @return esito della ricerca
     */
    boolean clienteExistsByEmail(Cliente cliente);

    /**
     * Il metodo cerca un cliente per id
     * @param id id del cliente da cercare
     * @return il cliente che corrisponde all'id
     */
    Cliente getClienteById(int id);
}
