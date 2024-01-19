package com.greenbridge.controllers;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.services.AgricoltoreServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@RestController
@RequestMapping("/api")
public class RestUseController {

    @Autowired
    private AgricoltoreServiceImpl agricoltoreService;

    @GetMapping("/Agricoltori")
    public List<Agricoltore> getAgricoltori() {
        return agricoltoreService.getAgricoltori();

    }

    @GetMapping("/Agricoltori/{id}")
    public Agricoltore getAgricoltore(@PathVariable int id) {
        return agricoltoreService.getSingleAgricoltore(id);
    }

    @PostMapping("/Agricoltori")
    public Agricoltore saveAgricoltore(@RequestBody Agricoltore agricoltore) {
        return agricoltoreService.saveAgricoltore(agricoltore);
    }
    @PostMapping("/modify/{id}")
    public ResponseEntity<String> modifyUserById(@PathVariable int id,
                         @RequestBody Agricoltore agricoltore) {
        Agricoltore a = agricoltoreService.getSingleAgricoltore(id);
        if (a != null && agricoltore.getNome().compareTo(a.getNome()) == 0) {
            agricoltoreService.modificaAgricoltore(agricoltore);
            return new ResponseEntity<>(" informazioni inseriti sono state "
                    + "aggiornate con successo", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.FORBIDDEN);
    }

    @PostMapping("/RegistrazioneUtente")
    public ResponseEntity<String> completaRegistrazione(@RequestBody
                                            Agricoltore agricoltore) {
        agricoltoreService.saveAgricoltore(agricoltore);
        return ResponseEntity.ok("Tutto ok!");
    }


}
