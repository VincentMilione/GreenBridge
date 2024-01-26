package com.greenbridge.controllers;

import com.greenbridge.entities.Agricoltore;
import com.greenbridge.services.AgricoltoreServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

import java.util.List;

/**
 * @author  elmehdi zitouni
 * Controller for handling operations
 * related to farmers through RESTful APIs.
 * Supported operations include
 * retrieving the list of farmers,
 * retrieving a single
 * farmer by ID, saving a new farmer,
 * modifying an existing farmer,
 * registering a new
 * farmer user, and adding a
 * certificate to a farmer.
 */
@RestController
@RequestMapping("/api")
public class RestUseController {
    /**
     * Servizio per la gestione delle operazioni
     * legate agli agricoltori.
     * Questo campo viene iniettato
     * automaticamente (autowired) da Spring.
     */
    @Autowired
    private AgricoltoreServiceImpl agricoltoreService;
    /**
     * Retrieves the list of all farmers.
     * @return List of farmers
     */
    @GetMapping("/Agricoltori")
    public List<Agricoltore> getAgricoltori() {
        return agricoltoreService.getAgricoltori();
    }
    /**
     * Retrieves a single farmer by ID.
     * @param id ID of the farmer to retrieve
     * @return Farmer corresponding to the provided ID
     */
    @GetMapping("/Agricoltori/{id}")
    public Agricoltore getAgricoltore(@PathVariable int id) {
        return agricoltoreService.getSingleAgricoltore(id);
    }
    /**
     * Saves a new farmer.
     * @param agricoltore Farmer object to save
     * @return Newly saved farmer
     */
    @PostMapping("/Agricoltori")
    public Agricoltore saveAgricoltore(
            @RequestBody Agricoltore agricoltore) {
        return agricoltoreService.saveAgricoltore(agricoltore);
    }
    /**
     * Modifies the information
     * of an existing farmer by ID.
     * @param id ID of the farmer to modify
     * @param agricoltore  New farmer information
     * @return ResponseEntity
     * containing a success or error message
     */
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
    /**
     * Completes the registration
     * of a new farmer user.
     * @param agricoltore New farmer user
     * to register
     * @return ResponseEntity containing
     * a confirmation message
     */
    @PostMapping("/RegistrazioneUtente")
    public ResponseEntity<String> completaRegistrazione(@RequestBody
                                            Agricoltore agricoltore) {
        agricoltoreService.saveAgricoltore(agricoltore);
        return ResponseEntity.ok("Tutto ok!");
    }
    /**
     * Adds a certificate to an existing farmer by ID.
     * @param id  ID of the farmer to add the certificate to
     * @param certName    Name of the certificate
     * @param expiryDate  Expiry date of the certificate
     * @param certScan    Certificate
     * file in MultipartFile format
     * @return ResponseEntity containing
     * a success or error message
     */
    @PostMapping("/Agricoltori/{id}/aggiungiCertificato")
    public ResponseEntity<String>
           aggiungiCertificato(
            @PathVariable int id,
            @RequestParam String certName,
            @RequestParam LocalDate expiryDate,
            @RequestParam MultipartFile certScan) {

             agricoltoreService.aggiungiCertificato(id,
                                               certName,
                                               expiryDate,
                                               certScan);
        return new ResponseEntity<>("Certificato aggiunto con successo",
                HttpStatus.OK);
    }
}
