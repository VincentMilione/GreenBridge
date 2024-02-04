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
 * Controller per gestire le operazioni
 * relative agli agricoltori attraverso API RESTful.
 * Le operazioni supportate includono
 * il recupero della lista degli agricoltori,
 * il recupero di un singolo
 * agricoltore tramite ID,
 * il salvataggio di un nuovo agricoltore,
 * la modifica di un agricoltore esistente,
 * la registrazione di un nuovo
 * utente agricoltore e l'aggiunta di un
 * certificato a un agricoltore.
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
     * Recupera la lista di tutti gli agricoltori.
     * @return Lista degli agricoltori
     */

    @GetMapping("/Agricoltori")
    public List<Agricoltore> getAgricoltori() {
        return agricoltoreService.getAgricoltori();
    }
    /**
     * Recupera un singolo agricoltore tramite ID.
     * @param id ID dell'agricoltore da recuperare
     * @return Agricoltore corrispondente all'ID fornito
     */
    @GetMapping("/Agricoltori/{id}")
    public Agricoltore getAgricoltore(@PathVariable int id) {
        return agricoltoreService.getSingleAgricoltore(id);
    }
    /**
     * Salva un nuovo agricoltore.
     * @param agricoltore Oggetto Agricoltore da salvare
     * @return Agricoltore appena salvato
     */
    @PostMapping("/Agricoltori")
    public Agricoltore saveAgricoltore(
            @RequestBody Agricoltore agricoltore) {
        return agricoltoreService.saveAgricoltore(agricoltore);
    }
    /**
     * Modifica le informazioni
     * di un agricoltore esistente tramite ID.
     * @param id ID dell'agricoltore da modificare
     * @param agricoltore Nuove informazioni dell'agricoltore
     * @return ResponseEntity
     * contenente un messaggio di successo o errore
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
     * Completa la registrazione
     * di un nuovo utente agricoltore.
     * @param agricoltore Nuovo utente agricoltore
     * da registrare
     * @return ResponseEntity contenente
     * un messaggio di conferma
     */
    @PostMapping("/RegistrazioneUtente")
    public ResponseEntity<String> completaRegistrazione(@RequestBody
                                            Agricoltore agricoltore) {
        agricoltoreService.saveAgricoltore(agricoltore);
        return ResponseEntity.ok("Tutto ok!");
    }
    /**
     * Aggiunge un certificato a un agricoltore
     * esistente tramite ID.
     * @param id  ID dell'agricoltore a cui aggiungere il certificato
     * @param certName    Nome del certificato
     * @param expiryDate  Data di scadenza del certificato
     * @param certScan    File del certificato
     * in formato MultipartFile
     * @return ResponseEntity contenente
     * un messaggio di successo o errore
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
