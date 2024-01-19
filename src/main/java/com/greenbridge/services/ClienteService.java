package com.greenbridge.services;

import com.greenbridge.entities.Cliente;


import java.util.List;

public interface ClienteService {
     void saveCliente(Cliente cliente);
     List<Cliente> getAllClienti();
     Cliente getClienteByEmail(String email);
     boolean clienteExistsByEmail(Cliente cliente);

}
