package com.greenbridge.repositories;

import com.greenbridge.entities.CarrelloCliente;
import com.greenbridge.entities.Cliente;
import com.greenbridge.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrelloClienteRepository
        extends JpaRepository<CarrelloCliente, Integer> {
                List<CarrelloCliente> findByCliente(Cliente cliente);

                void deleteByProdotto(Prodotto prodotto);

                void deleteByCliente(Cliente cliente);



        }
