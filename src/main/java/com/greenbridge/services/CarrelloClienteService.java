package com.greenbridge.services;

import com.greenbridge.entities.CarrelloCliente;
import com.greenbridge.entities.Cliente;
import com.greenbridge.entities.Prodotto;
import com.greenbridge.repositories.CarrelloClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Questa classe dispone una serie di metodi
 * che permettono di interagire con la classe CarrelloCliente.
 * @author Salvatore Mattiello
 */
@Service
public class CarrelloClienteService {

    /** repository del carrelloCliente.*/
    @Autowired
    private CarrelloClienteRepository carrelloClienteRepository;

    /**
     * Ottiene una lista CarrelloCliente dal cliente.
     *
     * @param cliente L'ID del portafoglio da ottenere.
     * @return lista CarrelloCliente corrispondente.
     * all'ID specificato.
     */
    public List<CarrelloCliente> getByClientId(Cliente cliente) {
        List<CarrelloCliente> carrelloCliente = carrelloClienteRepository.
                findByCliente(cliente);
        if (carrelloCliente.isEmpty()) {
            return null;
        }
        return carrelloCliente;

    }

    /**
     * Salva CarrelloCliente nel db.
     *
     * @param carrelloCliente Oggetto da salvare.

     */
    public void save(CarrelloCliente carrelloCliente) {
        if (carrelloCliente != null) {
            carrelloClienteRepository.save(carrelloCliente);
        }
    }

    /**
     * Elimina un CarrelloCliente che contiene tale prodotto.
     *
     * @param prodotto contenuto nell'istanza.
     */
    @Transactional
    public void deleteByProdotto(Prodotto prodotto) {
        carrelloClienteRepository.deleteByProdotto(prodotto);
    }

    /**
     * Elemina tutte le istanze di CarrelloCLiente di un cliente.
     *
     * @param cliente L'ID del cliente a cui appartiene.

     */
    @Transactional
    public void deleteAllByCliente(Cliente cliente) {
        carrelloClienteRepository.deleteByCliente(cliente);
    }
}