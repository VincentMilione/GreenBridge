package com.greenbridge.controllers;
import com.greenbridge.entities.Cliente;
import com.greenbridge.services.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ClienteRestController {
    @Autowired
    ClienteServiceImpl clienteService;
    @PostMapping("/saveCliente")
    public ResponseEntity<String> saveCliente(@RequestBody Cliente cliente){
        if (clienteService.clienteExistsByEmail(cliente))
            return new ResponseEntity<>("notok", HttpStatus.FORBIDDEN);
        clienteService.saveCliente(cliente);
        return new ResponseEntity<>("ok",HttpStatus.CREATED);
    }

}