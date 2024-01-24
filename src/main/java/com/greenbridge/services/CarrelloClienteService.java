package com.greenbridge.services;

import com.greenbridge.entities.CarrelloCliente;
import com.greenbridge.entities.Cliente;
import com.greenbridge.entities.Prodotto;
import com.greenbridge.repositories.CarrelloClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrelloClienteService {
    @Autowired
    private CarrelloClienteRepository carrelloClienteRepository;

    public List<CarrelloCliente> getByClientId(Cliente cliente) {
        List<CarrelloCliente> carrelloCliente = carrelloClienteRepository.
                findByCliente(cliente);
        if (carrelloCliente.isEmpty()) {
            return null;
        }
        return carrelloCliente;

    }

    public void save(CarrelloCliente carrelloCliente) {
        if (carrelloCliente != null) {
            carrelloClienteRepository.save(carrelloCliente);
        }
    }

    @Transactional
    public void deleteByProdotto(Prodotto prodotto) {
        carrelloClienteRepository.deleteByProdotto(prodotto);
    }

    @Transactional
    public void deleteAllByCliente(Cliente cliente) {
        carrelloClienteRepository.deleteByCliente(cliente);
    }
}