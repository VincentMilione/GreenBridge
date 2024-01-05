package com.greenbridge.services;

import com.greenbridge.entities.Cliente;
import com.greenbridge.repositories.ClienteRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ClienteService {
    public void saveCliente(Cliente cliente);
    public List<Cliente> getAllClienti();
    public Cliente getClienteByEmail(String email);
    public boolean clienteExistsByEmail(Cliente cliente);
}
