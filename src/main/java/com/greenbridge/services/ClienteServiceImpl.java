package com.greenbridge.services;

import com.greenbridge.entities.Cliente;
import com.greenbridge.repositories.ClienteRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl {
    private final ClienteRepository clienteRepository;
    public ClienteServiceImpl(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public void saveCliente(Cliente cliente){
        clienteRepository.save(cliente);
    }

    public List<Cliente> getAllClienti() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteByEmail(String email){
        return clienteRepository.findByEmail(email);
    }

    public boolean clienteExistsByEmail(Cliente cliente) {
        return clienteRepository.existsByEmail(cliente.getEmail());
    }

    public Cliente getClienteById(int id) {return clienteRepository.findById(id).get();}
}
