package com.greenbridge.controllers;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.services.AgricoltoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
public class RestUseController {

    @Autowired
    private AgricoltoreService agricoltoreService;
    @PostMapping("/api/modifica-utente")
    public ResponseEntity<String> modifyUser(@RequestBody Agricoltore agricoltore) {
        //TODO: process POST request
        agricoltoreService.modifyUser(agricoltore);
        return ResponseEntity.ok("Tutto ok!");
    }

    @PostMapping("/api/RegistrazioneUtente")
    public ResponseEntity<String> completaRegistrazione(@RequestBody Agricoltore agricoltore) {
        //TODO: process POST request
        agricoltoreService.save(agricoltore);
        return ResponseEntity.ok("Tutto ok!");
    }


}
